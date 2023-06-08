package com.example.servicesjebackcompose.view.screen.ItemDetails

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicesjebackcompose.model.PreferenceManager
import com.example.servicesjebackcompose.view.view_helper.OrderDetailsItem
import com.example.servicesjebackcompose.viewModel.GetPendingOrdersViewModel

@Composable
fun Pending (){

    val viewModel: GetPendingOrdersViewModel = viewModel()
    val listModel = viewModel.listPendingOrdersLiveData
    val context = LocalContext.current
    val pref = PreferenceManager(context)

    val token = pref.getToken()


    if (!token.isNullOrBlank()) {

        viewModel.getPendingOrders(token.toString())

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 55.dp)
        ) {


            itemsIndexed(items = listModel) { index, item ->
                OrderDetailsItem(model = item)
            }


        }
    } else {
        Box(
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

        }
        Toast.makeText(context, "Please Login to benefit from the service", Toast.LENGTH_SHORT)
            .show()
    }


}
