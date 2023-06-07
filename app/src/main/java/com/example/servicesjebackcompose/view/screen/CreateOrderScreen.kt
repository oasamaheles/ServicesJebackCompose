package com.example.servicesjebackcompose.view.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.servicesjebackcompose.R
import com.example.servicesjebackcompose.model.PreferenceManager
import com.example.servicesjebackcompose.view.items.CustomButton
import com.example.servicesjebackcompose.viewModel.CreateOrderViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


class CreateOrderScreen : ComponentActivity(), LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    private val createOrderViewModel: CreateOrderViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        setContent {


            val itemId = intent.getIntExtra("itemId", -1)
            val itemName = intent.getStringExtra("itemName")
            val navController = rememberNavController()
            val context = LocalContext.current

            val preferenceManager = PreferenceManager(context)
            val userId = preferenceManager.getUserID()


                NavHost(navController, startDestination = "createOrderProblemDetailsScreen") {


                    composable("createOrderDone") {
                        CreateOrderDone(navController)
                    }

                    composable("createOrderProblemDetailsScreen") {




                        if (itemName != null) {
                            val userPhone = preferenceManager.getUserPhone()

                            createOrderViewModel.newOrderRequestResponse.observe(this@CreateOrderScreen) { response ->

                                if (response.success == true) {
                                    navController.navigate("createOrderDone")
                                } else {
                                    Toast.makeText(
                                        this@CreateOrderScreen,
                                        "Error : ${response.message}",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }
                            }

                            createOrderViewModel.error.observe(this@CreateOrderScreen) { errorMessage ->
                                Toast.makeText(
                                    this@CreateOrderScreen,
                                    errorMessage,
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                            CreateOrderProblemDetailsScreen(
                                navController = navController,
                                itemId = itemId,
                                itemName = itemName,
                                userId = userId,
                                userPhone = userPhone.toString(), viewModel = createOrderViewModel
                            )
                        } else {
                            Toast.makeText(context, "No Service Name", Toast.LENGTH_LONG).show()
                        }

                    }


                }

        }
    }


    override fun onBackPressed() {
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

}

@Composable
fun CreateOrderProblemDetailsScreen(
    navController: NavHostController,
    itemId: Int,
    userId: Int,
    userPhone: String,
    itemName: String,
    viewModel: CreateOrderViewModel
) {


    val problemState = remember { mutableStateOf(TextFieldValue()) }
    val locationState = remember { mutableStateOf(TextFieldValue()) }

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }






    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xff346EDF), Color(0xff6FC8FB)),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY
                    ),
                    shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50)
                ),
        ) {

            val context = LocalContext.current
            IconButton(onClick = { navigateToHome(context = context) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 6.dp, bottom = 14.dp, top = 20.dp)
                        .align(alignment = Alignment.CenterStart)
                )
            }

            Text(
                text = itemName, style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    color = Color.White
                ), modifier = Modifier.align(alignment = Alignment.Center)
            )
        }



        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .border(
                    BorderStroke(1.dp, Color(0xFF868686)), shape = RoundedCornerShape(6.dp)
                ),
        ) {

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {

                Image(
                    painter = painterResource(id = R.drawable.image_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .align(alignment = Alignment.CenterStart)
                )

                Text(
                    text = "Image Problem", style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    ), modifier = Modifier.align(alignment = Alignment.Center)

                )
            }

        }

        Text(
            text = "image must be no more than 2 MB Max 5 Image", style = TextStyle(
                fontSize = 8.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Start,
                color = Color.Gray
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp)
        )


        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TextField(
                value = problemState.value,
                onValueChange = { problemState.value = it },
                label = {
                    Text(
                        text = "More Details About Problem …",
                        modifier = Modifier.padding(2.dp),
                        fontFamily = FontFamily.Cursive,

                        color = Color.Gray,
                        fontSize = 14.sp

                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.White)
                    .border(
                        border = BorderStroke(1.dp, Color(0xff346EDF)),
                        shape = RoundedCornerShape(8.dp),
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Start
                )
            )


        }


        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "London",
                snippet = "Marker in Big Ben"
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TextField(
                value = locationState.value,
                onValueChange = { locationState.value = it },
                label = {
                    Text(
                        text = "Location Address Details …",
                        modifier = Modifier.padding(2.dp),
                        fontFamily = FontFamily.Cursive,
                        color = Color.Gray,
                        fontSize = 14.sp

                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.White)
                    .border(
                        border = BorderStroke(1.dp, Color(0xff346EDF)),
                        shape = RoundedCornerShape(8.dp),
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Start
                )
            )


        }

        val context = LocalContext.current

        CustomButton(
            onClick = {

                val problemDes = problemState.value.text
                val locationDes = locationState.value.text
                val preferenceManager = PreferenceManager(context)
                val userToken = preferenceManager.getToken()

                viewModel.createOrder(
                    userToken.toString(),
                    userId,
                    itemId,
                    problemDes,
                    locationDes,
                    singapore.latitude.toString(),
                    singapore.longitude.toString(),
                    userPhone,

                    )


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                .height(64.dp),
            gradientColors = listOf(
                Color(0xff346EDF), Color(0xff6FC8FB)
            )
        ) {
            Text(
                text = "Create Order",
                fontSize = 16.sp,
                fontFamily = FontFamily.Cursive,

                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }


    }


}

@Composable
fun CreateOrderDone(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.create_done_img),
                contentDescription = "Create done img",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 64.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = "ORDER Done!",
                color = Color(0xff0E9CF9),
                fontSize = 23.sp,
                fontFamily = FontFamily.Cursive,

                textAlign = TextAlign.Center,
            )

            Text(
                text = "The ORDER has been sent. A technician will contact you",
                color = Color(0xFF636363),
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 12.sp,
                fontFamily = FontFamily.Cursive,

                textAlign = TextAlign.Center,
            )



            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                val context = LocalContext.current
                CustomButton(
                    onClick = { navigateToHome(context) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(64.dp)
                        .align(alignment = Alignment.BottomStart)
                        .padding(8.dp),
                    gradientColors = listOf(Color(0xff346EDF), Color(0xff6FC8FB))
                ) {
                    Text(
                        text = "Go to Home",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Cursive,

                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }


        }

    }

}

private fun navigateToHome(context: Context) {
    val intent = Intent(context, HomeScreen::class.java).apply {}
    context.startActivity(intent)
}





