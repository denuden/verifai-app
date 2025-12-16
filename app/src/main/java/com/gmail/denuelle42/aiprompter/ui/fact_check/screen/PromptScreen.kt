package com.gmail.denuelle42.aiprompter.ui.fact_check.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens
import com.gmail.denuelle42.aiprompter.ui.common.AppTitle
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenEvents
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckScreenState
import com.gmail.denuelle42.aiprompter.ui.fact_check.FactCheckViewModel
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.TextFieldPrompt
import com.gmail.denuelle42.aiprompter.utils.ObserveAsEvents
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents

@Composable
fun PromptScreen(
    onNavigate: (NavigationScreens) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: FactCheckViewModel = hiltViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    PromptScreenContent(
        modifier = Modifier.fillMaxSize(),
        uiState = state,
        onEvent = viewModel::onEvent
    )

    ObserveAsEvents(viewModel.channel) { event ->
        when(event){
            is OneTimeEvents.OnNavigate -> {
                onNavigate(event.route)
            }
            is OneTimeEvents.OnPopBackStack -> {
                onPopBackStack()
            }
            else -> Unit
        }
    }
}

@Composable
fun PromptScreenContent(
    modifier: Modifier = Modifier,
    uiState: FactCheckScreenState,
    onEvent: (FactCheckScreenEvents) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    //TODO
                },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = Color.DarkGray
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
                    .size(48.dp)
            ) {
                Text("JK", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                AppTitle()

                Text(
                    text = stringResource(R.string.lbl_tagline),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(R.string.lbl_tagline_subtitle),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 4.dp)
                )

                TextFieldPrompt(
                    modifier = Modifier.padding(top = 16.dp),
                    onTextChange = {
                        onEvent(FactCheckScreenEvents.OnChangeTextPrompt(it))
                    },
                    textValue = uiState.textPrompt.orEmpty(),
                    onEnter = {
                        onEvent(FactCheckScreenEvents.OnNavigateToChatScreen(uiState.textPrompt.orEmpty()))
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                FilledIconButton(
                    onClick = {
                        onEvent(FactCheckScreenEvents.OnNavigateToHistoryScreen)
                    },
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ListAlt,
                        contentDescription = stringResource(R.string.lbl_history)
                    )
                }
                Text(text = stringResource(R.string.lbl_history), style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}

@Preview
@Composable
private fun PromptScreenPreview() {
    VerifaiTheme {
        PromptScreenContent(
            uiState = FactCheckScreenState(),
            onEvent = {}
        )
    }
}