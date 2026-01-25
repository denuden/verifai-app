package com.gmail.denuelle42.aiprompter.ui.fact_check.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenEvents
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenState
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckViewModel
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.HistoryListItem
import com.gmail.denuelle42.aiprompter.utils.ComposableLifecycle
import com.gmail.denuelle42.aiprompter.utils.ObserveAsEvents
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents

@Composable
fun HistoryScreen(
    onNavigate: (NavigationScreens) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: FactCheckViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.onEvent(FactCheckScreenEvents.OnGetAllFactChecks)
        }
    }

    ObserveAsEvents(viewModel.channel) { event ->
        when (event) {
            is OneTimeEvents.OnNavigate -> {
                onNavigate(event.route)
            }

            is OneTimeEvents.OnPopBackStack -> {
                onPopBackStack()
            }

            is OneTimeEvents.ShowToast -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    HistoryScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = state,
        onEvent = viewModel::onEvent
    )


}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HistoryScreenContent(
    modifier: Modifier = Modifier,
    uiState: FactCheckScreenState,
    onEvent: (FactCheckScreenEvents) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceDim,
        modifier = modifier
    ) {
        Column {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.height(56.dp)
                    .fillMaxWidth()
            ) {
                IconButton(onClick = {
                    onEvent(FactCheckScreenEvents.OnNavigateBack)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
            }


            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)

            ) {
                items(uiState.getAllFactCheckResponse.size) { index ->
                    val item = uiState.getAllFactCheckResponse[index]
                    //Check if at the bottom of list
                    if (index >= uiState.getAllFactCheckResponse.size - 1 && !uiState.endReached && !uiState.isGetAllFactCheckLoading) {
                        onEvent(FactCheckScreenEvents.OnGetAllFactChecks)
                    }

                    HistoryListItem(
                        data = item
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    if (uiState.isGetAllFactCheckLoading) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize().padding(8.dp)
                        ) {
                            CircularWavyProgressIndicator()
                            Text(stringResource(R.string.lbl_loading_your_history))
                        }
                    }
                }
            }
        }


    }
}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    VerifaiTheme {
        HistoryScreenContent(uiState = FactCheckScreenState(), onEvent = {})
    }
}