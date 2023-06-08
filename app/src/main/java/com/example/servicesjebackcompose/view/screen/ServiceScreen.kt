package com.example.servicesjebackcompose.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicesjebackcompose.view.view_helper.ItemWorkServiceCard
import com.example.servicesjebackcompose.viewModel.AllWorkViewModel
import com.example.servicesjebackcompose.R
import com.example.servicesjebackcompose.model.PreferenceManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ServiceScreen() {

    val viewModel: AllWorkViewModel = viewModel()
    val listModel = viewModel.listAllWorkLiveData
    val context = LocalContext.current



    viewModel.getAllWork()







//    val AllWorkViewModel:ViewModel = AllWorkViewModel()



    //todo 800701




    Column(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material.Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(254.dp)
                .weight(1.3f),
            shape = RoundedCornerShape(0.dp, 0.dp, 110.dp, 110.dp),
            backgroundColor = colorResource(id = R.color.background_card),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .align(Alignment.CenterHorizontally) // Align the column content horizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_app),
                            contentDescription = "image app",
                            modifier = Modifier
                                .width(53.09.dp)
                                .height(33.35.dp)
                                .align(alignment = Alignment.Center)
                        )
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .align(alignment = Alignment.TopEnd)
                                .padding(end = 19.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.shape),
                                contentDescription = "Notifications",
                                modifier = Modifier.size(30.dp),
                                tint = Color.White // Set the tint color to white
                            )
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                val states = rememberPagerState(initialPage = 0)
                                val slideImage =
                                    remember { mutableStateOf(R.drawable.image_splach_1) }
                                Card(
                                    modifier = Modifier
                                        .width(250.dp)
                                        .height(100.dp),
                                    shape = RoundedCornerShape(10.dp),
                                ) {
                                    LaunchedEffect(Unit) {
                                        while (true) {
                                            delay(3000) // Adjust the delay as needed (e.g., 3000ms = 3 seconds)
                                            val nextPage =
                                                (states.currentPage + 1) % states.pageCount
                                            states.scrollToPage(nextPage)
                                        }
                                    }

                                    HorizontalPager(count = 3, state = states) { page ->
                                        when (page) {
                                            0 -> {
                                                slideImage.value = R.drawable.image_splach_1
                                            }
                                            1 -> {
                                                slideImage.value = R.drawable.image_splach_2
                                            }
                                            2 -> {
                                                slideImage.value = R.drawable.splash_home
                                            }
                                        }

                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Image(
                                                painterResource(slideImage.value),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .height(87.dp)
                                                    .width(202.dp),
                                                contentScale = ContentScale.Fit
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.padding(4.dp))

                                DotsIndicator(
                                    totalDots = states.pageCount,
                                    selectedIndex = states.currentPage,
                                    selectedColor = Color.Blue,
                                    unSelectedColor = Color.White
                                )
                            }
                        }
                    }


                }
            }
        }

        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .weight(4f)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Select Service",
                    style = TextStyle(
                        fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Blue
                    )
                )
            } // Inside the composable where you have the LazyVerticalGrid
            LazyVerticalGrid(
                GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 2.dp, vertical = 30.dp)

            ) {
                itemsIndexed(items = listModel) { index, item ->
                    ItemWorkServiceCard(model = item, onItemClick = {

                        val preferenceManager = PreferenceManager(context)
                        val userId = preferenceManager.getUserID()

                        if (userId != -1) {
                            item.id?.let {
                                navigateToCreateOrderClass(
                                    context = context,
                                    it,
                                    itemName = item.name.toString(),
                                    icons = item.icon
                                )
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Please Login to Create order",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            navigateToLoginScreen(context)
                        }

                    })

                }
            }
        }

    }


}

@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }

}

private fun navigateToCreateOrderClass(
    context: Context,
    itemId: Int,
    itemName: String,
    icons: String?
) {
    val intent = Intent(context, CreateOrderScreen::class.java).apply {
        putExtra("itemId", itemId)
        putExtra("itemName", itemName)
    }
    context.startActivity(intent)
}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {

    }
    context.startActivity(intent)
}





