package com.example.servicesjebackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.servicesjebackcompose.view.view_helper.BottomAppBarItems
import com.example.servicesjebackcompose.view.screen.MoreScreen
import com.example.servicesjebackcompose.view.screen.OrdersScreen
import com.example.servicesjebackcompose.view.screen.ServiceScreen
import com.example.servicesjebackcompose.view.screen.UserScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomAppBarItems.Service.route
    ) {
        composable(route = BottomAppBarItems.Service.route) {
            ServiceScreen()

        }
        composable(route = BottomAppBarItems.Orders.route) {
            OrdersScreen()
        }
        composable(route = BottomAppBarItems.User.route) {
            UserScreen()
        }
        composable(route = BottomAppBarItems.More.route) {
            MoreScreen()
        }


    }
}