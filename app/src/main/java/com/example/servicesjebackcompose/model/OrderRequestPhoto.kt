package com.example.servicesjebackcompose.model

import com.google.gson.annotations.SerializedName

data class OrderRequestPhoto(

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("photo"      ) var photo     : String? = null,
    @SerializedName("order_id"   ) var orderId   : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null
)