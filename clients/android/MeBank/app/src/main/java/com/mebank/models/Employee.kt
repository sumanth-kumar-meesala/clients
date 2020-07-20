package com.mebank.models

data class Employee(
    val id: Number,
    val first_name: String,
    val last_name: String,
    val birth_date: String,
    val gender: String,
    val thumbImage: String?,
    val image: String?
)