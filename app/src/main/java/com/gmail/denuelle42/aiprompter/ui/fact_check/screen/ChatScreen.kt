import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens
import com.gmail.denuelle42.aiprompter.ui.common.dialog.ErrorDialog
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenEvents
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenState
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckViewModel
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.chat.ChatResponseFlow
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.StatusBox
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.bubble.SenderChatBubble
import com.gmail.denuelle42.aiprompter.utils.ComposableLifecycle
import com.gmail.denuelle42.aiprompter.utils.ObserveAsEvents
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import com.gmail.denuelle42.aiprompter.utils.SnackBarController
import com.gmail.denuelle42.aiprompter.utils.extractUrl
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    onNavigate : (NavigationScreens) -> Unit,
    onPopBackStack : () -> Unit,
    statement : String,
    viewModel : FactCheckViewModel = hiltViewModel()
) {
    val state : FactCheckScreenState by viewModel.stateFlow.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    ErrorDialog(
        text = errorMessage,
        showDialog = showErrorDialog
    ) {
        showErrorDialog = false
    }

    ComposableLifecycle { source, event ->
        if(event == Lifecycle.Event.ON_RESUME) {
            //initialize and get statement and update it
            viewModel.onEvent(FactCheckScreenEvents.OnChangeTextPrompt(statement))
            scope.launch {
                if(state.textPrompt.isNullOrEmpty()){
                    showErrorDialog = true
                    errorMessage = "Statement is empty"
                } else {
                    viewModel.onEvent(FactCheckScreenEvents.OnCreateFactCheck(CreateFactCheckRequest(
                        statement = state.textPrompt.orEmpty()
                    )))
                }
            }
        }
    }

    ObserveAsEvents(viewModel.channel) { event ->
        when(event){
            is OneTimeEvents.OnNavigate -> {
                onNavigate(event.route)
            }
            is OneTimeEvents.OnPopBackStack -> {
                onPopBackStack()
            }
            is OneTimeEvents.ShowSnackbar -> {
                scope.launch {
                    SnackBarController.sendEvent(event.snackbarEvent)
                }
            }
            is OneTimeEvents.ShowToast -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }

            is OneTimeEvents.ShowError -> {
                showErrorDialog = true
                errorMessage = event.msg
            }
            else -> Unit
        }
    }

    ChatScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ChatScreenContent(
    modifier: Modifier = Modifier,
    uiState : FactCheckScreenState,
    onEvent : (FactCheckScreenEvents) -> Unit,
) {
    val scrollState = rememberScrollState()

    // Auto-scroll to bottom when new content appears
    LaunchedEffect(scrollState.maxValue) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceDim,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                // User Prompt
                if (!uiState.textPrompt.isNullOrEmpty()) {
                    SenderChatBubble(
                        text = uiState.textPrompt
                    )
                }

                // Response Flow
                // Only show this if we actually have data loaded in the state
                // Assuming uiState.factCheckData is your parsed JSON object
                uiState.createFactCheckResponse?.data?.let { data ->
                    ChatResponseFlow(
                        data = data,
                    )
                }
            }

            // Status Box (Thinking...)
            // Hide this when we have data so the typing animation takes over
            StatusBox(modifier = Modifier.fillMaxWidth(), isThinking = uiState.isCreateFactCheckLoading){
                onEvent(FactCheckScreenEvents.OnNavigateToPromptScreen)
            }
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    VerifaiTheme {
        ChatScreenContent(
            uiState = FactCheckScreenState(),
            onEvent =  {

            }
        )
    }
}