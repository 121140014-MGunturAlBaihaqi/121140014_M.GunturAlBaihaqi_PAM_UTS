package com.example.a121140014_pam_uts

data class UserResponse(
    val data: List<User>
)

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)