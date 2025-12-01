package com.gmail.denuelle42.aiprompter.ui.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens

@Composable
fun SampleScreen(
    onPopBackStack : () -> Unit,
    onNavigate : (NavigationScreens) -> Unit
) {
    SampleScreenContent()
}

@Composable
fun SampleScreenContent(modifier: Modifier = Modifier) {

}