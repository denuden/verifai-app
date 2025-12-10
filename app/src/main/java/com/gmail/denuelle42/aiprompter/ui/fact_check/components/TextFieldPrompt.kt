package com.gmail.denuelle42.aiprompter.ui.fact_check.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R

@Composable
fun TextFieldPrompt(
    textValue : String,
    onTextChange : (String) -> Unit,
    modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.inverseSurface),
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextField(
                value = textValue,
                onValueChange = {onTextChange(it)},
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                minLines = 5,
                maxLines = 5,
                placeholder = {
                    Text(text = stringResource(R.string.hint_ai_is_here_to_help_you))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Mic,
                        contentDescription = stringResource(R.string.lbl_speech_to_text)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                FilledIconButton(
                    onClick = {},
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = stringResource(R.string.lbl_speech_to_text)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TextFieldPromptPreview() {
    VerifaiTheme {
        TextFieldPrompt(
            textValue = "",
            onTextChange = {}
        )
    }
}