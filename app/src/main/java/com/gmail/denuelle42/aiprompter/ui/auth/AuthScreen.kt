package com.gmail.denuelle42.aiprompter.ui.auth

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens
import com.gmail.denuelle42.aiprompter.ui.auth.login.LoginScreenContent
import com.gmail.denuelle42.aiprompter.ui.auth.register.RegisterScreenContent
import com.gmail.denuelle42.aiprompter.ui.common.dialog.ErrorDialog
import com.gmail.denuelle42.aiprompter.ui.common.dialog.LoadingDialog
import com.gmail.denuelle42.aiprompter.utils.ObserveAsEvents
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import com.gmail.denuelle42.aiprompter.utils.SnackBarController
import com.gmail.denuelle42.aiprompter.utils.handleInputError
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    onPopBackStack: () -> Unit,
    onNavigate: (NavigationScreens) -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    ErrorDialog(
        text = errorMessage,
        showDialog = showErrorDialog
    ) {
        showErrorDialog = false
    }

    //One time events listener
    ObserveAsEvents(flow = viewModel.channel) { event ->
        when (event) {
            is OneTimeEvents.OnNavigate -> onNavigate(event.route)
            OneTimeEvents.OnPopBackStack -> onPopBackStack()
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

            is OneTimeEvents.ShowInputError -> {

            }
        }
    }

    //login loading
    LoadingDialog(text = "Signing in...", showDialog = state.isLoginLoading) { }
    //register loading
    LoadingDialog(text = "Registering...", showDialog = state.isRegisterLoading) { }

    AuthScreenContent(uiState = state, onEvent = viewModel::onEvent)
}

@Composable
fun AuthScreenContent(
    uiState: AuthScreenState,
    onEvent: (AuthScreenEvents) -> Unit
) {
    var isLogin by remember { mutableStateOf(true) }

    val transition = updateTransition(targetState = isLogin, label = "authTransition")

    // Animate background positions
    val topColor by transition.animateColor(label = "topColor") { state ->
        if (state) MaterialTheme.colorScheme.inversePrimary
        else MaterialTheme.colorScheme.surface
    }

    val bottomColor by transition.animateColor(label = "bottomColor") { state ->
        if (state) MaterialTheme.colorScheme.surfaceContainer
        else MaterialTheme.colorScheme.inversePrimary
    }

    // 1. ANIMATE THE WEIGHT FOR A SLIDING EFFECT
    val topWeight by transition.animateFloat(
        label = "topWeight",
        transitionSpec = { tween(durationMillis = 500) }
    ) { state ->
        if (state) 1f else 0.6f // When login, top is bigger. When register, it's smaller.
    }

    val bottomWeight by transition.animateFloat(
        label = "bottomWeight",
        transitionSpec = { tween(durationMillis = 500) }
    ) { state ->
        if (state) 0.6f else 1f // The opposite of the top weight
    }


    // Card fade + slide
    val cardAlpha by transition.animateFloat(label = "cardAlpha") { if (it) 1f else 1f }

    val scroll = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        // ANIMATED BACKGROUND
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(topWeight) // Use animated weight
                    .background(topColor)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(bottomWeight) // Use animated weight
                    .background(bottomColor)
            )
        }

        // MAIN CONTENT
        Column(modifier = Modifier
            .align(Alignment.Center)
            .imePadding()
            .verticalScroll(scroll)
        ) {

            // LOGO
            Text(
                text = stringResource(R.string.app_name).uppercase(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            // FADING CARD
            Box(
                modifier = Modifier
                    .alpha(cardAlpha)
                    .animateContentSize(animationSpec = tween(durationMillis = 300))
            ) {
                Crossfade(
                    targetState = isLogin,
                    animationSpec = tween(500)
                ) { state ->
                    if (state) {
                        LoginScreenContent(
                            uiState = uiState,
                            onEvent = onEvent,
                            animeToRegister = {
                                isLogin = false
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    } else {
                        RegisterScreenContent(
                            uiState = uiState,
                            onEvent = onEvent,
                            animeToLogin = {
                                isLogin = true
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    VerifaiTheme {
        AuthScreenContent(uiState = AuthScreenState(), onEvent = { })
    }
}