package com.gmail.denuelle42.aiprompter.ui.fact_check.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.R
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import com.gmail.denuelle42.aiprompter.utils.formatIsoDateAsDateTime

@Composable
fun HistoryListItem(
    modifier: Modifier = Modifier,
    data: FactCheckModel
) {
    val verdictColor =
        if (data.verdict.toBoolean()) colorResource(R.color.verdict_true) else colorResource(R.color.verdict_false)

    OutlinedCard(
        border = BorderStroke(width = 1.dp, color = verdictColor),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Row {
                Text(
                    text = data.statement.orEmpty(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = verdictColor,
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = formatIsoDateAsDateTime(
                    data.created_at.orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
private fun HistoryListItemPreview() {
    VerifaiTheme {
        HistoryListItem(
            data = FactCheckModel(
                statement = "Gatchalian takes over 'MIA' Bato's budget duties at Senate",
                explanation = "Senator Sherwin Gatchalian took over the budget duties of Senator Ronald \\\"Bato\\\" dela Rosa due to Dela Rosa's absence during Senate plenary budget deliberations. Gatchalian is now managing and defending the Department of National Defense (DND) budget on the Senate floor.",
                created_at = "2025-12-12T09:42:56+00:00",
                verdict = "true"
            )
        )
    }
}