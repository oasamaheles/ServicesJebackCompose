package com.example.servicesjebackcompose.model

import com.google.gson.annotations.SerializedName

data class OrderRequestData(
    @SerializedName("id"              ) var id             : Int?                  = null,
    @SerializedName("user_id"         ) var userId         : Int?                  = null,
    @SerializedName("delivery_id"     ) var deliveryId     : String?               = null,
    @SerializedName("work_id"         ) var workId         : Int?                  = null,
    @SerializedName("details"         ) var details        : String?               = null,
    @SerializedName("details_address" ) var detailsAddress : String?               = null,
    @SerializedName("lat"             ) var lat            : String?               = null,
    @SerializedName("long"            ) var long           : String?               = null,
    @SerializedName("phone"           ) var phone          : String?               = null,
    @SerializedName("status"          ) var status         : Int?                  = null,
    @SerializedName("created_at"      ) var createdAt      : String?               = null,
    @SerializedName("updated_at"      ) var updatedAt      : String?               = null,
    @SerializedName("photo_order"     ) var photoOrder     : ArrayList<OrderRequestPhoto> = arrayListOf(),
    @SerializedName("work"            ) var work           : OrderRequestWork?                 = OrderRequestWork()
)