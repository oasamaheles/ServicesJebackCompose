package com.example.servicesjebackcompose.view.view_helper

import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.servicesjebackcompose.model.OrderRequestData
import com.example.servicesjebackcompose.model.OrderRequestPhoto
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun OrderDetailsItem(model: OrderRequestData) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentWidth()
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

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {

                Text(
                    text = "Order id:  ${model.id}",
                    style = TextStyle(

                        color = Color(0xFF488CE7),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ), modifier = Modifier
                        .padding(bottom = 6.dp)
                )


                val inst: OffsetDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    OffsetDateTime.ofInstant(
                        Instant.parse(model.createdAt.toString()),
                        ZoneId.systemDefault()
                    )
                } else {
                    TODO("VERSION.SDK_INT < O")
                }

                val res = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DateTimeFormatter.ofPattern("MMM dd, yyyy").format(inst)
                } else {
                    TODO("VERSION.SDK_INT < O")
                }


                Text(
                    text = "Date : $res",
                    style = TextStyle(
                        color = Color.Gray,

                        fontSize = 8.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                )
//
//                model.photoOrder?.let { resourceId ->
//                    val painter = painterResource(resourceId)
//                    Image(
//                        painter = painter,
//                        contentDescription = "Image",
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(start = 8.dp)
//                    )
//                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {

                Text(
                    text = "Service Type : ${model.work?.name}",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start,
                        

                        ), modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = "Carpenter",
                    style = TextStyle(
                        

                        color = Color(0xFF488CE7),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Start
                    ), modifier = Modifier
                        .padding(bottom = 6.dp, start = 2.dp)
                        .weight(1f)
                        .align(alignment = Alignment.CenterVertically)
                )

            }


        }
    }

}
@Composable
fun LoadImageFromUrl(url: ArrayList<OrderRequestPhoto>) {
    val painter: Painter = rememberImagePainter(url)
    Image(
        painter = painter,
        contentDescription = "Image",
        modifier = Modifier
            .size(40.dp)
            .padding(start = 8.dp)
    )
}


