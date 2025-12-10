import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.VerifaiTheme
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens

@Composable
fun ChatScreen(
    onNavigate : (NavigationScreens) -> Unit,
    onPopBackStack : () -> Unit,
) {

}

@Composable
fun ChatScreenContent(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun ChatScreenPreview() {
    VerifaiTheme {
        ChatScreenContent(

        )
    }
}