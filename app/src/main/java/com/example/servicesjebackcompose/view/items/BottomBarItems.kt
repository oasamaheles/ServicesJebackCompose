package com.example.servicesjebackcompose.view.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.servicesjebackcompose.R

sealed class BottomBarItems(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Service : BottomBarItems(
        route = "Service",
        title = "Service",
        icon = R.drawable.app_icon
    )

    object Orders : BottomBarItems(
        route = "Orders",
        title = "Orders",
        icon = R.drawable.oreder_icon
    )

    object User : BottomBarItems(
        route = "User",
        title = "User",
        icon = R.drawable.user_icon
    )

    object More : BottomBarItems(
        route = "More",
        title = "More",
        icon = R.drawable.more_icon
    )


}