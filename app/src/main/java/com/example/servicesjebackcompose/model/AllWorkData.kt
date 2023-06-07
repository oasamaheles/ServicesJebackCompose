package com.example.servicesjebackcompose.model

import com.google.gson.annotations.SerializedName


data class AllWorkData (

  @SerializedName("id"          ) var id          : Int?    = null,
  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("icon"        ) var icon        : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("active"      ) var active      : Int?    = null,
  @SerializedName("created_at"  ) var createdAt   : String? = null,
  @SerializedName("updated_at"  ) var updatedAt   : String? = null

)