package com.example.servicesjebackcompose.view.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.servicesjebackcompose.R
import com.example.servicesjebackcompose.model.PreferenceManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navMainController: NavController) {
    val startColor = Color(0xC7346EDF) // Green color
    val endColor = Color(0xFF6FC8FB)
    val mexColor = listOf(startColor, endColor)
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current
    val preferenceManager = PreferenceManager(context)
    val tokenCheck =preferenceManager.getToken().isNullOrBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = mexColor
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(Unit) {
            delay(2600)
            if (tokenCheck) {
                preferenceManager.saveUserId(-1)
                navMainController.navigate("OnBoarding_one")

            } else {
                navigateToHomeScreen(context)
            }
        }
        Image(
            painter = painterResource(R.drawable.splash_img), contentDescription = null,
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
                .padding(start = 100.dp)
        )
    }

    // Reset status bar color when the component is removed from the screen
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycleScope.launchWhenStarted {
            systemUiController.setStatusBarColor(
                color = mexColor.first(), darkIcons = false
            )
        }
    }
}

private fun navigateToHomeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navMainController = NavController(LocalContext.current))
}
