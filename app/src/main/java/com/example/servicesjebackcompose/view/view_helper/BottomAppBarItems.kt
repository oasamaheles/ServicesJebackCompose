package com.example.servicesjebackcompose.view.view_helper

import com.example.servicesjebackcompose.R

sealed class BottomAppBarItems(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Service : BottomAppBarItems(
        route = "Service",
        title = "Service",
        icon = R.drawable.app_icon
    )

    object Orders : BottomAppBarItems(
        route = "Orders",
        title = "Orders",
        icon = R.drawable.oreder_icon
    )

    object User : BottomAppBarItems(
        route = "User",
        title = "User",
        icon = R.drawable.user_icon
    )

    object More : BottomAppBarItems(
        route = "More",
        title = "More",
        icon = R.drawable.more_icon
    )


}