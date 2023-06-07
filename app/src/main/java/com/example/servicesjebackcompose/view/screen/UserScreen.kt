package com.example.servicesjebackcompose.view.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.servicesjebackcompose.R
import com.example.servicesjebackcompose.model.PreferenceManager


@Composable
fun UserScreen() {
    val btnStartColor = Color(0xEC047EE0)
    val btnEndColor = Color(0xFF29B6F6)
    Column(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(btnStartColor, btnEndColor)
                )
            )
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 20.dp, end = 24.dp),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                onClick = { /* Handle icon button click */ }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = "Icon",
                    tint = Color.White
                )

            }

        }

        Column(
            modifier = Modifier
                .weight(7f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .height(120.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 220.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit),
                            tint = Color(0xFF0E4DFB),
                            contentDescription = "Icon Edit",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Seraj Al Mutawa",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = "Riyadh, Saudi Arabia",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .background(Color(0xFFF2F3F7))
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {
                ViewItem("Language", "English", onRowClick = {})
                Spacer(modifier = Modifier.height(32.dp))
                ViewItem("My Rates", "",onRowClick = {})
                Spacer(modifier = Modifier.height(32.dp))
                ViewItem("Contact us","", onRowClick = {})
                Spacer(modifier = Modifier.height(32.dp))
                ViewItem("Share App","", onRowClick = {})
            }
            Spacer(
                modifier = Modifier
                    .background(Color(0xFFF2F3F7))
                    .fillMaxWidth()
                    .height(10.dp)
            )

            Column(modifier = Modifier
                .fillMaxWidth()) {
                val context = LocalContext.current
                val preferenceManager = PreferenceManager(context)
                Row(
                    modifier = Modifier
                        .clickable(onClick = {
                            preferenceManager.deleteToken()
                            preferenceManager.deleteUserId()
                            preferenceManager.deleteUserUserPhone()
                            navigateToLoginScreen(context)
                        })
                        .fillMaxWidth()
                        .height(54.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_join),
                        contentDescription = "Icon Sign Out",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFFAF8344)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "SIGN OUT",
                        color = Color(0xFF0E4DFB),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,

                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .background(Color(0xFFF2F3F7))
                    .fillMaxWidth()
                    .weight(1f),
            )
        }


    }
    Box(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val shapeSize = 104.dp

        Box(
            modifier = Modifier
                .height(130.dp)
                .width(130.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(R.drawable.image_profiel), // Replace with your image resource ID
                contentDescription = "Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(shapeSize)
                    .clip(RoundedCornerShape(16.dp))
                    .height(shapeSize),
                contentScale = ContentScale.Crop

            )
        }
    }

}

@Composable
fun ViewItem(title: String, titleTypeLanguage: String, onRowClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onRowClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Text(text = titleTypeLanguage, color = Color(0xFFC2CECE))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next",
                tint = Color(0xFFC2CECE)
            )
        }
    }
}

private fun navigateToLoginScreen(context: Context) {
    val intent = Intent(context, LoginScreen::class.java).apply {}
    context.startActivity(intent)
}






