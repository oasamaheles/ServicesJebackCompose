package com.example.servicesjebackcompose.model

data class LoginResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val data: UserData
)