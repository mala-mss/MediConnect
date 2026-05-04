package com.mediconnect.data.model

data class Speciality(
    val id: Int,
    val name: String,
    val doctorCount: Int,
    val bgColor: Int,
    val iconRes: Int = 0
)
