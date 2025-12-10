package com.gmail.denuelle42.aiprompter.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name).uppercase(),
        style = MaterialTheme.typography.displayLarge,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
            .padding(bottom = 8.dp)
    )
}

@Preview
@Composable
private fun AppTitlePreview() {
    VerifaiTheme {
        AppTitle()
    }
}