package com.example.androidexampleroom

import java.util.*

data class User(
        val username: String,
        val age: Int,
        val isCool: Boolean,
        val id: String = UUID.randomUUID().toString()
)