package com.example.moneymanagerapp.request

data class LoginResponse(
    val token: String,
    val user: User
)