package com.example.servicesjebackcompose.view.items

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.servicesjebackcompose.model.AllWorkData


@Composable
fun ItemWorkServiceCard(model: AllWorkData, onItemClick: () -> Unit) {


    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentWidth()
            .clickable{onItemClick()}
            .border(
                border = BorderStroke(1.dp, Color(0xFF488CE7)),
                shape = MaterialTheme.shapes.medium
            )
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            AsyncImage(
                model = model.icon,
                contentDescription = "icon",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Text(
                text = model.name.toString(),
                style = TextStyle(
                    fontFamily= FontFamily.Cursive,
                    color = Color(0xFF488CE7),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(bottom = 6.dp)
            )

        }
    }
}



