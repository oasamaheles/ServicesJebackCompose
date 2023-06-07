package com.example.servicesjebackcompose.view.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import com.example.servicesjebackcompose.model.OrderRequestData
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun OrderDetailsItem(model: OrderRequestData) {


    Card(
        modifier = Modifier
            .padding(16.dp)
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

            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {

                Text(
                    text = "Order id:  ${model.id}",
                    style = TextStyle(
                        fontFamily = FontFamily.Cursive,
                        color = Color(0xFF488CE7),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Start
                    ), modifier = Modifier
                        .padding(bottom = 6.dp)
                )


                val inst: OffsetDateTime = OffsetDateTime.ofInstant(
                    Instant.parse(model.createdAt.toString()),
                    ZoneId.systemDefault()
                )

                val res =  DateTimeFormatter.ofPattern("MMM dd, yyyy").format(inst)

                Text(
                    text = "Date : $res",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 8.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f)
                )

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
                        fontFamily = FontFamily.Cursive,

                        ), modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = "Carpenter",
                    style = TextStyle(
                        fontFamily = FontFamily.Cursive,

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
