package com.example.servicesjebackcompose.view.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.servicesjebackcompose.navigation.BottomNavGraph
import com.example.servicesjebackcompose.view.items.BottomBarItems
import com.example.servicesjebackcompose.R;

class HomeScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(
                bottomBar = { ManagementAllScreenBottomNavigation(navController = navController) }
            ) {
                BottomNavGraph(navController = navController)
            }
            requestPermissionsIfNecessary()
        }


    }
    override fun onBackPressed() {
    }

    private fun requestPermissionsIfNecessary() {
        val missingPermissions = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 123
        private val requiredPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
            // Add any other required permissions here
        )
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ManagementAllScreenBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val items = listOf(
        BottomBarItems.Service, BottomBarItems.Orders, BottomBarItems.User, BottomBarItems.More
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(84.dp),
                backgroundColor = colorResource(id = R.color.background_card)
            ) {
                items.forEach { item ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 5.dp),
                        icon = {
                            Image(
                                painterResource(id = item.icon),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp)
                                    .padding(bottom = 6.dp)
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = TextStyle(color = Color.White, fontSize = 11.sp),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        },
                        selected = currentDestination?.route == item.route,
                        selectedContentColor = Color.White,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        Column {
            BottomNavGraph(navController = navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { ManagementAllScreenBottomNavigation(navController = navController) }
    ) {
        Column {
            BottomNavGraph(navController = navController)
        }
    }
}
