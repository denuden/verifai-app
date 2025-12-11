package com.gmail.denuelle42.aiprompter.ui.fact_check.components.bubble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.CustomReceiverBubbleShape

@Composable
fun ReceiverChatBubble(
    modifier: Modifier = Modifier,
    content : @Composable () -> Unit,
    ) {

    val bubbleColor = MaterialTheme.colorScheme.surface


    // Align to the end (right) for sender
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Use a Surface for automatic background sizing and text contrast
        Surface(
            color = bubbleColor,
            shape = CustomReceiverBubbleShape(cornerRadius = 32.dp.value), // Custom Shape
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(min = 300.dp) // Don't let it stretch too wide
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}





@Preview
@Composable
private fun ReceiverChatBubblePreview() {
    VerifaiTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
            ReceiverChatBubble(
            ){}
        }
    }
}