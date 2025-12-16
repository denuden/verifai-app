package com.gmail.denuelle42.aiprompter.ui.fact_check.components.chat

import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.bubble.ReceiverChatBubble
import com.gmail.denuelle42.aiprompter.utils.TypewriterText
import com.gmail.denuelle42.aiprompter.utils.extractUrl
import kotlinx.coroutines.delay

@Composable
fun ChatResponseFlow(
    data: FactCheckModel,
) {
    // States to control visibility sequence
    var showSources by remember { mutableStateOf(false) }
    var showVerdict by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        // 1. EXPLANATION (Starts immediately)
        if (data.explanation.orEmpty().isNotEmpty()) {
            ReceiverChatBubble {
                TypewriterText(
                    text = data.explanation.orEmpty(),
                    modifier = Modifier.padding(16.dp),
                    onFinished = {
                        // When explanation finishes, wait a bit then show sources
                        showSources = true
                    }
                )
            }
        }

        // 2. SOURCES (Shows after Explanation)
        if (showSources && data.sources.orEmpty().isNotEmpty()) {
            // We use a LaunchedEffect here to trigger the next step 
            // after the cards are "rendered" (or add a fake delay for effect)
            LaunchedEffect(Unit) {
                delay(500) // Small pause before showing verdict
                showVerdict = true
            }
            ReceiverChatBubble {
                Column {
                    data.sources.orEmpty().forEach { source ->
                        if (source != null) {
                            Spacer(Modifier.height(8.dp))
                            LinkPreviewCard(source)
                        }
                    }
                }
            }
        } else if (showSources && data.sources.orEmpty().isEmpty()) {
            // Handle case with no sources to continue flow
            SideEffect { showVerdict = true }
        }

        // 3. VERDICT (Shows after Sources)
        if (showVerdict && data.verdict.orEmpty().isNotEmpty()) {
            ReceiverChatBubble {
                TypewriterText(
                    text = "Verdict: ${data.verdict.orEmpty().uppercase()}",
                    style = MaterialTheme.typography.bodyMedium, // Make it bold/larger
                    modifier = Modifier.padding(16.dp),
                    onFinished = {
                        showMessage = true
                    }
                )
            }
        }

        // 4. FINAL MESSAGE (Shows after Verdict)
        if (showMessage) {
            ReceiverChatBubble {
                TypewriterText(
                    text = stringResource(R.string.lbl_verdict_successful_msg),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}