package com.gmail.denuelle42.aiprompter.ui.fact_check.components.chat

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.LinkMetaDataModel
import com.gmail.denuelle42.aiprompter.utils.AsyncImageWithErrorHandler
import androidx.core.net.toUri

@Composable
fun LinkPreviewCard(meta: LinkMetaDataModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ElevatedCard (
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),

        shape = MaterialTheme.shapes.medium,
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, meta.finalUrl.toUri())
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            meta.image?.let { imageUrl ->
                AsyncImageWithErrorHandler(
                    model = imageUrl,
                    contentDescription = meta.title.orEmpty(),
                    shouldShowEnlargeButton = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                )
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = meta.title ?: meta.finalUrl,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )

                meta.description?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(Modifier.height(6.dp))
                Text(
                    text = meta.finalUrl,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Light,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun LinkPreviewCardPreview() {
    VerifaiTheme {
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