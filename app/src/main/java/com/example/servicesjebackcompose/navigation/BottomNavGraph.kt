package com.example.servicesjebackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.servicesjebackcompose.view.items.BottomBarItems
import com.example.servicesjebackcompose.view.screen.MoreScreen
import com.example.servicesjebackcompose.view.screen.OrdersScreen
import com.example.servicesjebackcompose.view.screen.ServiceScreen
import com.example.servicesjebackcompose.view.screen.UserScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.Service.route
    ) {
        composable(route = BottomBarItems.Service.route) {
            ServiceScreen()

        }
        composable(route = BottomBarItems.Orders.route) {
            OrdersScreen()
        }
        composable(route = BottomBarItems.User.route) {
            UserScreen()
        }
        composable(route = BottomBarItems.More.route) {
            MoreScreen()
        }


    }
}