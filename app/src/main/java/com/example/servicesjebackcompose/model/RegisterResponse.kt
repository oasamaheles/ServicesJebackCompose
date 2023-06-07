package com.example.servicesjebackcompose.model

data class RegisterResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val data: UserData
)