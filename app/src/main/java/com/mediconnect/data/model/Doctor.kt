package com.mediconnect.data.model

data class Doctor(
    val id: Int,
    val name: String,
    val speciality: String,
    val experience: String,
    val rating: Float,
    val patients: String,
    val yearsExp: String,
    val phone: String,
    val email: String,
    val about: String,
    val emoji: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)
