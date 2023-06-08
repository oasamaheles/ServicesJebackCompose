package com.example.servicesjebackcompose.view.view_helper

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDropdownMenu(items: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .height(60.dp)
                .border(
                    BorderStroke(1.dp, Color(0xFF484849)),
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable { expanded = true }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    tint = Color.Gray,
                    contentDescription = "Drop menu arrow"
                )
                Text(
                    text = if (selectedItem.isNotEmpty()) selectedItem else "Select  service",
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f),
                    fontSize = 15.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = Color.Gray,
                    contentDescription = "Drop menu arrow"
                )

            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = item
                        expanded = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}