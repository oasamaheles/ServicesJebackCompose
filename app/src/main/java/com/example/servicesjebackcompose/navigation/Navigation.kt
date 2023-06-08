package com.example.servicesjebackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.servicesjebackcompose.view.screen.*

@Composable
fun Navigation() {
    val navMainController = rememberNavController()

    NavHost(navController = navMainController, startDestination = "SplashScreen") {
        composable(route = "SplashScreen") {
            SplashScreen(navMainController = navMainController)
        }

        composable(route = "OnBoarding_one") {
            OnBoardingScreen(navMainController = navMainController)
        }

        composable(route = "OnBoarding_two") {
            OnBoardingScreen2(navMainController = navMainController)
        }

        composable(route = "OnBoarding_three") {
            OnBoardingScreen3(navMainController = navMainController)
        }
//
//        composable(route = "ChangePassword") {
//            ChangePassword(navMainController = navMainController)
//        }

    }
}