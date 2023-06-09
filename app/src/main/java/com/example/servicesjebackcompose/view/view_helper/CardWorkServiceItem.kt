package com.example.servicesjebackcompose.view.view_helper

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.servicesjebackcompose.model.AllWorkData
import androidx.compose.ui.res.painterResource
import  com.example.servicesjebackcompose.R



@Composable
fun ItemWorkServiceCard(model: AllWorkData, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(86.dp)
            .width(93.dp)
            .wrapContentWidth()
            .clickable { onItemClick() }
            .border(
                border = BorderStroke(1.dp, Color(0xFFDE1487)),
                shape = MaterialTheme.shapes.medium
            )
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .height(86.dp)
                .width(93.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = model.icon,
                contentDescription = "icon",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                placeholder = painterResource(id = R.drawable.service_1),

            )
            Text(
                text = model.name.toString(),
                style = TextStyle(
                    color = Color(0xFF488CE7),
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(bottom = 6.dp)
            )

        }
    }
}
