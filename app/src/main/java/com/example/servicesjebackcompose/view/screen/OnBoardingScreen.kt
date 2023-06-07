package com.example.servicesjebackcompose.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.servicesjebackcompose.R

@Composable
fun OnBoardingScreen(navMainController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.splash_home),
            contentDescription = "onBoardingImage",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Fast reservation with technicians and craftsmen",
            color = Color(0xFF0E9CF9),
            style = MaterialTheme.typography.h5.copy(fontSize = 21.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()
        )

        val btnEndColor = Color(0xFF6FC8FB)
        val btnStartColor = Color(0xFF346EDF)
        TextButton(
            onClick = { navMainController.navigate("OnBoardingScreen2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp, bottom = 34.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(btnStartColor, btnEndColor)
                    )
                ),
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

    }
}


