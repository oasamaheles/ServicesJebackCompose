package com.example.servicesjebackcompose.view.view_helper

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonApp(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    gradientColors: List<Color>,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(0.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = gradientColors,
                                startX = 0f,
                                endX = size.width
                            )
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}