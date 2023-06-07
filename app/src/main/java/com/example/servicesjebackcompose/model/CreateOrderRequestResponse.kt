package com.example.servicesjebackcompose.model

import com.google.gson.annotations.SerializedName

data class CreateOrderRequestResponse(
    @SerializedName("code"    ) var code    : Int?            = null,
    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : List<OrderRequestData> = arrayListOf()
)
