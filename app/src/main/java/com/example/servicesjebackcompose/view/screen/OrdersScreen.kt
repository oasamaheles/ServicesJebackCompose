package com.example.servicesjebackcompose.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicesjebackcompose.model.PreferenceManager
import com.example.servicesjebackcompose.view.items.OrderDetailsItem
import com.example.servicesjebackcompose.view.screen.ItemDetails.Completed
import com.example.servicesjebackcompose.view.screen.ItemDetails.Pending
import com.example.servicesjebackcompose.view.screen.ItemDetails.Underway
import com.example.servicesjebackcompose.viewModel.GetCompleteOrders
import com.example.servicesjebackcompose.viewModel.GetPendingOrdersViewModel
import com.example.servicesjebackcompose.viewModel.GetUnCompleteOrders
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OrdersScreen() {
    val startColor = Color(0xC7346EDF) // Green color
    val endColor = Color(0xFF6FC8FB)
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(startColor, endColor)
                    )
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Item Details",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        color = Color(0xFF0E4DFB)
                    )
                },
                backgroundColor = Color.White
            ) {
                tabRowItems.forEachIndexed { index, item ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = item.title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 14.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                count = tabRowItems.size,
                state = pagerState
            ) {
                tabRowItems[pagerState.currentPage].screen()
            }
        }
    }
}

val tabRowItems = listOf(
    TabRowItem(
        title = "Pending",
        screen = {
            Pending()
        },
    ),
    TabRowItem(
        title = "Underway",
        screen = {
            Completed()
        },
    ),
    TabRowItem(
        title = "Completed",
        screen = {
            Underway()
        },
    )
)

data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit,
)


@Preview(showBackground = true)
@Composable
fun DefaultPreviews() {
    OrdersScreen()
}




//
//
//
//
//data class TabRowItem(
//    val title: String,
//    val screen: @Composable () -> Unit,
//
//    )
//
//val tabRowItems = listOf(
//
//    TabRowItem(
//        title = "Pending",
//        screen = { PendingOrder() },
//    ), TabRowItem(
//        title = "Underway",
//        screen = { UnderwayOrder() },
//    ), TabRowItem(
//        title = "Completed ",
//        screen = { CompletedOrder() },
//    )
//)
//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun OrdersScreen() {
//
//    val pagerState = rememberPagerState()
//    val coroutineScope = rememberCoroutineScope()
//
//
//    Column(Modifier.fillMaxSize()) {
//
//
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .height(80.dp)
//                .background(
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
//                        startX = 0f,
//                        endX = Float.POSITIVE_INFINITY
//                    ),
//                ),
//        ) {
//            Text(
//                text = "Order Details", style = TextStyle(
//                    fontSize = 18.sp,
//                    fontFamily = FontFamily.Cursive,
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                ), modifier = Modifier.align(alignment = Alignment.Center)
//            )
//
//
//        }
//
//        TabRow(
//            backgroundColor = Color.White, contentColor = Color(0xFF636363),
//
//            selectedTabIndex = pagerState.currentPage,
//            indicator = { tabPositions ->
//                TabRowDefaults.Indicator(
//                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
//                    color = Color(0xFF266CE2)
//                )
//            },
//        ) {
//            tabRowItems.forEachIndexed { index, item ->
//                Tab(selected = pagerState.currentPage == index,
//                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
//                    text = {
//                        Text(
//                            text = item.title,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis,
//                            style = TextStyle(
//                                fontSize = 12.sp,
//                                fontFamily = FontFamily.Cursive,
//                                textAlign = TextAlign.Start
//                            )
//                        )
//                    })
//            }
//        }
//        HorizontalPager(
//            count = tabRowItems.size,
//            state = pagerState,
//        ) {
//            tabRowItems[pagerState.currentPage].screen()
//        }
//    }
//}
//
//@Composable
//fun PendingOrder() {
//
//    val viewModel: GetPendingOrdersViewModel = viewModel()
//    val listModel = viewModel.listPendingOrdersLiveData
//    val context = LocalContext.current
//    val pref = PreferenceManager(context)
//
//    val token = pref.getToken()
//
//
//    if (!token.isNullOrBlank()) {
//
//        viewModel.getPendingOrders(token.toString())
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(1),
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 20.dp, bottom = 55.dp)
//        ) {
//
//
//            itemsIndexed(items = listModel) { index, item ->
//                OrderDetailsItem(model = item)
//            }
//
//
//        }
//    } else {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(color = Color.White)
//        ) {
//
//        }
//        Toast.makeText(context, "Please Login to show your order", Toast.LENGTH_SHORT)
//            .show()
//    }
//
//
//}
//
//@Composable
//fun CompletedOrder() {
//
//
//    val viewModel: GetCompleteOrders = viewModel()
//    val listModel = viewModel.listCompleteOrderLiveData
//    val context = LocalContext.current
//
//    val pref = PreferenceManager(context)
//
//    val token = pref.getToken()
//
//
//    if (!token.isNullOrBlank()) {
//
//        viewModel.getCompleteOrders(token.toString())
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(1),
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 20.dp, bottom = 55.dp)
//        ) {
//
//
//            itemsIndexed(items = listModel) { index, item ->
//                OrderDetailsItem(model = item)
//            }
//
//
//        }
//    } else {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(color = Color.White)
//        ) {
//
//        }
//        Toast.makeText(context, "Please Login to show your order", Toast.LENGTH_SHORT)
//            .show()
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun UnderwayOrder() {
//    val viewModel: GetUnCompleteOrders = viewModel()
//    val listModel = viewModel.listUnCompleteOrderLiveData
//    val context = LocalContext.current
//    val pref = PreferenceManager(context)
//
//    val token = pref.getToken()
//
//
//
//
//
//
//    if (!token.isNullOrBlank()) {
//        viewModel.getUnCompleteOrders(token.toString())
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(1),
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 20.dp, bottom = 55.dp)
//        ) {
//
//
//            itemsIndexed(items = listModel) { index, item ->
//                OrderDetailsItem(model = item)
//            }
//
//
//        }
//    } else {
//        Box(
//            Modifier
//                .fillMaxSize()
//                .background(color = Color.White)
//        ) {
//
//        }
//        Toast.makeText(context, "Please Login to show your order", Toast.LENGTH_SHORT)
//            .show()
//    }
//}
//
