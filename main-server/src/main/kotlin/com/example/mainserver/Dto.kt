package com.example.mainserver

data class UpdateTeam(
    val id: Long,
    val name: String,
    val description: String
)

data class UpdateMember(
    val id: Long,
    val name: String,
    val age: Int
)