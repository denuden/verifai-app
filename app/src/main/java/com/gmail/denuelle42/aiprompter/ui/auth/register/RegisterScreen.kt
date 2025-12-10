package com.gmail.denuelle42.aiprompter.ui.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreenEvents
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreenState
import com.gmail.denuelle42.aiprompter.utils.clickableDelayed

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    uiState: AuthScreenState,
    onEvent: (AuthScreenEvents) -> Unit,
    animeToLogin: () -> Unit,
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors().copy(
            containerColor = Color.White
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.lbl_register).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            //NAME
            TextField(
                value = uiState.name.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnNameChange(it))
                },
                isError = !uiState.nameError.isNullOrEmpty(),
                supportingText = {
                    uiState.nameError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
                },
                label = {
                    Text(text = stringResource(R.string.lbl_name))
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PersonOutline,
                        contentDescription = stringResource(R.string.lbl_name)
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 24.dp)
            )


            // EMAIL
            TextField(
                value = uiState.email.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnEmailChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.lbl_email))
                },
                isError = !uiState.emailError.isNullOrEmpty(),
                supportingText = {
                    uiState.emailError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                modifier = Modifier.padding(top = 8.dp)
            )

            //PASSWORD
            TextField(
                value = uiState.password.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnPasswordChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.lbl_password))
                },
                isError = !uiState.passwordError.isNullOrEmpty(),
                supportingText = {
                    uiState.passwordError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
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

            //PASSWORD CONFIRMATION
            TextField(
                value = uiState.passwordConfirmation.orEmpty(),
                onValueChange = {
                    onEvent(AuthScreenEvents.OnPasswordConfirmationChange(it))
                },
                label = {
                    Text(text = stringResource(R.string.lbl_password_confirmation))
                },
                isError = !uiState.passwordConfirmationError.isNullOrEmpty(),
                supportingText = {
                    uiState.passwordConfirmationError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
                },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LockOpen,
                        contentDescription = stringResource(R.string.lbl_password_confirmation)
                    )
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )


            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = stringResource(R.string.lbl_already_have_an_account),
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(R.string.lbl_sign_in),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickableDelayed {
                            animeToLogin()
                        }
                )
            }

            FilledTonalButton(
                onClick = {
                    onEvent(
                        AuthScreenEvents.OnRegister(
                            name = uiState.name.orEmpty(),
                            email = uiState.email.orEmpty(),
                            password = uiState.password.orEmpty(),
                            password_confirmation = uiState.passwordConfirmation.orEmpty(),
                        )
                    )
                },
                modifier = Modifier
                    .padding(top = 32.dp)
                    .width(164.dp)
            ) {
                Text(
                    text = stringResource(R.string.lbl_register),
                    style = MaterialTheme.typography.titleSmall,
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
            RegisterScreenContent(
                uiState = AuthScreenState(),
                onEvent = { },
                animeToLogin = {}
            )
        }
    }
}