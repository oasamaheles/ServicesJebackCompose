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
import com.example.servicesjebackcompose.view.screen.ItemDetails.Completed
import com.example.servicesjebackcompose.view.screen.ItemDetails.Pending
import com.example.servicesjebackcompose.view.screen.ItemDetails.Underway
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

