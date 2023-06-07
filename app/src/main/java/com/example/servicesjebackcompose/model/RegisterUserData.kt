package com.example.servicesjebackcompose.model


data class RegisterUserData (
    val name: String,
    val email: String,
    val phone: String,
    val active: String,
    val type_user: String,
    val token: String
)