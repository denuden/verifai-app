package com.gmail.denuelle42.aiprompter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.navigation.AuthScreens
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreenEvents
import com.gmail.denuelle42.aiprompter.ui.auth.AuthViewModel
import com.gmail.denuelle42.aiprompter.utils.ComposableLifecycle
import com.gmail.denuelle42.aiprompter.utils.ObserveAsEvents
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onFinished: (isLoggedIn : Boolean) -> Unit,
    viewModel : AuthViewModel = hiltViewModel()
) {

    ObserveAsEvents(viewModel.channel) {
        when(it){
            is OneTimeEvents.OnNavigate -> {
                //if success
                if(it.route == AuthScreens.SplashNavigation){
                    onFinished(true)
                } else { //if unauthorized
                    onFinished(false)
                }
            }
            else -> Unit
        }
    }

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_START) {
            scope.launch {
                delay(1000)
                viewModel.onEvent(AuthScreenEvents.OnRefreshToken)
            }
        }
    }

  
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "AI PROMPTER",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    VerifaiTheme {
        Surface(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .fillMaxSize()
        ) {
//            SplashScreen() {}
        }
    }
}