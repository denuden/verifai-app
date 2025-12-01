package com.gmail.denuelle42.denuboilerplate.ui.sample

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import com.gmail.denuelle42.denuboilerplate.navigation.NavigationScreens

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