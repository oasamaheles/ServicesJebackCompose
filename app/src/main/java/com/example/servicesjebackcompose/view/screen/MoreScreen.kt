package com.example.servicesjebackcompose.view.screen
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import com.example.servicesjebackcompose.R
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoreScreen() {
        val backgroundPainter: Painter = painterResource(id = R.drawable.background_add_problem)
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.height(100.dp)) {
                // Background image
                Image(
                    painter = backgroundPainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )
                // Back button and title
                Row(
                    modifier = Modifier
                        .padding(top = 25.dp, start = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* Handle back button click */ },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = "More",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 35.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                ViewItem("Change Password", onRowClick = {})
                Spacer(modifier = Modifier.height(33.dp))
                ViewItem("FAQ's", onRowClick = {})
                Spacer(modifier = Modifier.height(33.dp))
                ViewItem("Terms & Conditions", onRowClick = {})
                Spacer(modifier = Modifier.height(33.dp))
                ViewItem("Privacy Policy", onRowClick = {})
                Spacer(modifier = Modifier.height(33.dp))
                ViewItem("Rate App", onRowClick = {})
                Spacer(modifier = Modifier.height(33.dp))
                ViewItem("Delete Account", onRowClick = {})


            }


        }
    }

    @Composable
    fun ViewItem(title: String, onRowClick: () -> Unit) {
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
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    tint = Color(0xFFC2CECE)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MoreActivity() {
        MoreScreen()

    }
