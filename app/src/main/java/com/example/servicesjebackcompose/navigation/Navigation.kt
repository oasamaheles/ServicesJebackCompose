package com.example.servicesjebackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.servicesjebackcompose.view.screen.OnBoardingScreen
import com.example.servicesjebackcompose.view.screen.OnBoardingScreen2
import com.example.servicesjebackcompose.view.screen.OnBoardingScreen3
import com.example.servicesjebackcompose.view.screen.SplashScreen

@Composable
fun Navigation() {
    val navMainController = rememberNavController()

    NavHost(navController = navMainController, startDestination = "SplashScreen") {
        composable(route = "SplashScreen") {
            SplashScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen") {
            OnBoardingScreen(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen2") {
            OnBoardingScreen2(navMainController = navMainController)
        }

        composable(route = "OnBoardingScreen3") {
            OnBoardingScreen3(navMainController = navMainController)
        }



    }
}