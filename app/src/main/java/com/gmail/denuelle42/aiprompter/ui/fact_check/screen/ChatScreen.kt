import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.LinkPreviewCard
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.StatusBox
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.bubble.ReceiverChatBubble
import com.gmail.denuelle42.aiprompter.ui.fact_check.components.bubble.SenderChatBubble

@Composable
fun ChatScreen(
    onNavigate : (NavigationScreens) -> Unit,
    onPopBackStack : () -> Unit,
    statement : String,
) {

}

@Composable
fun ChatScreenContent(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Surface(
        color = MaterialTheme.colorScheme.surfaceDim,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                SenderChatBubble(
                    text = "500 pesos is enough for noche buena"
                )
                ReceiverChatBubble {
                    Text(
                        text = "While the Department of Trade and Industry (DTI) claims a 500-peso Noche Buena is possible for a family of four with a pared-down menu, this claim has been met with skepticism and criticism, with many considering it unrealistic given rising food prices. The feasibility depends heavily on family size and the specific dishes included.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp) // Inner padding for text
                    )
                }

                ReceiverChatBubble {
                    LinkPreviewCard(
                        LinkMetaDataModel(
                            url = "https://vertexaisearch.cloud.google.com/grounding-api-redirect/AUZIYQH40I-0g_dWdZ89KSs55aWmddT_W9DSKLBambv8UzT5iqaADql9d3w5OLcRDxm6zAM6B54kmvWiF5pQ8Jn3aMbOMKaIJoetLOCNAnu9fITV63V9DSsTVNQu8W7DzR_bblnbNeV2Q-qOlKW1s82Nzas--IDYKf_fEmEJU5qeTRBKqLkhfZFxVuL-YWraESIHAB6vh_Y26Fvacu8bIJ0f7BXoU_j227WVPk4lnk2Vu8md-HH9bBbU4ILNtA==",
                            finalUrl = "https://www.gmanetwork.com/news/money/personalfinance/967840/p500-noche-buena-possible-for-family-of-four-dti-clarifies/story/",
                            image = "https://images.gmanews.tv/webpics/2021/07/grocery_items_2021_07_21_21_35_07.JPG",
                            title = "P500 Noche Buena possible for family of four, DTI clarifies",
                            description = "The Department of Trade and Industry (DTI) on Friday clarified that a 500-pesos Noche Buena remains feasible this year, but only for a family of four and with a pared-down menu."
                        )
                    )
                }
            }

            //Status Box
            StatusBox(modifier = Modifier.fillMaxWidth(), isThinking = false)
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    VerifaiTheme {
        ChatScreenContent(

        )
    }
}