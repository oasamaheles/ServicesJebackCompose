package com.example.servicesjebackcompose.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicesjebackcompose.apiService.ApiService
import com.example.servicesjebackcompose.model.OrderRequestData
import kotlinx.coroutines.launch

class GetUnCompleteOrders : ViewModel() {

    var listUnCompleteOrderLiveData: List<OrderRequestData> by mutableStateOf(listOf())


    fun getUnCompleteOrders(token:String) {
        viewModelScope.launch {
            val apiService = ApiService.geInstance()
            val getUnComplete = apiService.getUnCompletedOrder(token)

            listUnCompleteOrderLiveData = getUnComplete.data


        }


    }
}