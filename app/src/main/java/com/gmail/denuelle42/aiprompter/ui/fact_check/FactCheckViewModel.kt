package com.gmail.denuelle42.aiprompter.ui.fact_check

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.denuelle42.aiprompter.data.remote.error.ErrorModel
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import com.gmail.denuelle42.aiprompter.domain.repositories.fact_check.FactCheckUseCase
import com.gmail.denuelle42.aiprompter.navigation.FactCheckScreens
import com.gmail.denuelle42.aiprompter.ui.sample.SampleViewModel
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import com.gmail.denuelle42.aiprompter.utils.network.ResultState
import com.gmail.denuelle42.aiprompter.utils.network.asResult
import com.gmail.denuelle42.aiprompter.utils.pagination.DefaultPaginator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FactCheckViewModel @Inject constructor(
    private val factCheckUseCase: FactCheckUseCase,
) : ViewModel() {
    private val TAG = SampleViewModel::class.java.simpleName

    private val _channel = Channel<OneTimeEvents>()
    val channel = _channel.receiveAsFlow()

    private val _stateFlow = MutableStateFlow<FactCheckScreenState>(FactCheckScreenState())
    val stateFlow = _stateFlow.asStateFlow()

    private val getAllFactChecksPaginator = DefaultPaginator<Int, FactCheckModel>(
        initialKey = _stateFlow.value.page,
        onLoadUpdated = { isLoading ->
            _stateFlow.update { it.copy(isGetAllFactCheckLoading = isLoading) }
        },
        onRequest = { nextPage ->
            factCheckUseCase.getAllFactChecks(page = nextPage)
        },
        getNextKey = {
            _stateFlow.value.page + 1
        },
        onError = { error ->
            _stateFlow.update { it.copy(getAllFactCheckError = error?.localizedMessage) }
            sendEvent(OneTimeEvents.ShowError(error?.localizedMessage ?: "Unknown error"))
        },
        onSuccess = { items, newKey ->
            _stateFlow.update {
                it.copy(
                    getAllFactCheckResponse = it.getAllFactCheckResponse + items,
                    page = newKey,
                    endReached = items.isEmpty()
                )
            }
        }
    )

    private fun loadNextItems() {
        viewModelScope.launch {
            getAllFactChecksPaginator.loadNextItems()
        }
    }

    fun onEvent(event : FactCheckScreenEvents) {
        when(event){
            is FactCheckScreenEvents.OnChangeTextPrompt -> {
                _stateFlow.update { it.copy(textPrompt = event.value) }
            }
            is FactCheckScreenEvents.OnCreateFactCheck -> {
                viewModelScope.launch {
                    factCheckUseCase.createFactCheck(event.request).asResult().onEach { res ->
                        when(res){
                            ResultState.Completed -> _stateFlow.update { it.copy(isCreateFactCheckLoading = false) }
                            is ResultState.Error -> onError(res.exception)
                            ResultState.Loading -> _stateFlow.update { it.copy(isCreateFactCheckLoading = true) }
                            is ResultState.Success -> {
                                _stateFlow.update {
                                    it.copy(createFactCheckResponse = res.data)
                                }
                            }
                        }
                    }.collect()
                }
            }

            is FactCheckScreenEvents.OnGetAllFactChecks -> {
                loadNextItems()
            }

            is FactCheckScreenEvents.OnShowFactCheck -> {

            }

            is FactCheckScreenEvents.OnNavigateToChatScreen -> {
                sendEvent(OneTimeEvents.OnNavigate(FactCheckScreens.ChatNavigation(event.value)))
            }

            FactCheckScreenEvents.OnNavigateToPromptScreen -> {

            }
            FactCheckScreenEvents.OnNavigateToHistoryScreen -> {
                sendEvent(OneTimeEvents.OnNavigate(FactCheckScreens.HistoryNavigation))
            }
        }
    }

    private fun onError(e: Throwable?) {
        when (e) {
            is HttpException -> {
                val errorBody = e.response()?.errorBody()
                val gson = Gson()
                val type = object : TypeToken<ErrorModel>() {}.type
                val errorResponse: ErrorModel? = gson.fromJson(errorBody?.charStream(), type)

                //if this is not null, then there is a message regarding bad request of params
                if (errorResponse?.errors != null) {
                    _stateFlow.update {
                        it.copy(
                            textPromptError = errorResponse.errors.statement?.get(0)
                        )
                    }
                    sendEvent(OneTimeEvents.ShowInputError(errorResponse.errors))
                } else if (errorResponse?.message != null) {
                    sendEvent(OneTimeEvents.ShowError(errorResponse.message))
                }
            }
        }
    }
    private fun sendEvent(event: OneTimeEvents) {
        viewModelScope.launch {
            _channel.send(event)
        }
    }
}