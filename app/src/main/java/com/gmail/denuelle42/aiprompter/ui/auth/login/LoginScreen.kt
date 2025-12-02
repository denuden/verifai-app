package com.gmail.denuelle42.aiprompter.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreenEvents
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreenState
import com.gmail.denuelle42.aiprompter.utils.clickableDelayed

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: AuthScreenState,
    onEvent: (AuthScreenEvents) -> Unit,
    animeToRegister: () -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors().copy(
            containerColor = Color.White
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.lbl_sign_in).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            TextField(
                value = uiState.email.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnEmailChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.lbl_email))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = stringResource(R.string.lbl_email)
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 40.dp)
            )

            TextField(
                value = uiState.password.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnPasswordChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.lbl_password))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LockOpen,
                        contentDescription = stringResource(R.string.lbl_password)
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = stringResource(R.string.lbl_don_t_have_an_account)
                )
                Text(
                    text = stringResource(R.string.lbl_register_here),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickableDelayed {
                            animeToRegister()
                        }
                )
            }

            FilledTonalButton(
                onClick = {
                    onEvent(
                        AuthScreenEvents.OnLogin(
                            uiState.email.orEmpty(),
                            uiState.password.orEmpty()
                        )
                    )
                },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .width(164.dp)
            ) {
                Text(
                    text = stringResource(R.string.btn_login),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }


}

@Preview
@Composable
private fun LoginScreenPreview() {
    VerifaiTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
            LoginScreenContent(
                uiState = AuthScreenState(),
                onEvent = { },
                animeToRegister = {}
            )
        }
    }
}