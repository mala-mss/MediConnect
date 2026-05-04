package com.mediconnect.data.model

import android.graphics.Color
import com.mediconnect.R

object SampleData {

    val doctors = mutableListOf(

        // Cardiology
        Doctor(
            id = 1,
            name = "Dr. Cherifi Lotfi",
            speciality = "Cardiologist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 11 78",
            email = "",
            about = "Cardiologist in Mila focusing on diagnosis and management of cardiovascular diseases.",    
            emoji = "?????",
            imageRes = R.drawable.dr_cherifi_lotfi,
            isFavorite = false
        ),

        // Neurology
        Doctor(
            id = 2,
            name = "Dr. Bensalem Nadia",
            speciality = "Neurologist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 44 21",
            email = "",
            about = "Neurologist dealing with disorders of the nervous system.",
            emoji = "?????",
            imageRes = R.drawable.dr_bensalem_nadia,
            isFavorite = false
        ),

        // Radiology
        Doctor(
            id = 3,
            name = "Dr. Hamdani Mourad",
            speciality = "Radiologist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 89 33",
            email = "",
            about = "Radiologist specializing in imaging techniques such as X-rays and scans.",
            emoji = "?????",
            imageRes = R.drawable.dr_hamdani_mourad,
            isFavorite = false
        ),

        // Dentistry
        Doctor(
            id = 4,
            name = "Dr. Saidi Samira",
            speciality = "Dentist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 67 12",
            email = "",
            about = "Dentist providing oral health care and cosmetic dental treatments.",
            emoji = "?????",
            imageRes = R.drawable.dr_saidi_samira,
            isFavorite = false
        ),

        // Orthopedics
        Doctor(
            id = 5,
            name = "Dr. Bougherara Abdelhak",
            speciality = "Orthopedic",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 23 45",
            email = "",
            about = "Orthopedic specialist treating bone and joint conditions.",
            emoji = "?????",
            imageRes = R.drawable.dr_bougherara_abdelhak,
            isFavorite = false
        ),

        // Ophthalmology
        Doctor(
            id = 6,
            name = "Dr. Meziane Farida",
            speciality = "Ophthalmologist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 55 90",
            email = "",
            about = "Eye specialist managing vision and ocular diseases.",
            emoji = "?????",
            imageRes = R.drawable.dr_meziane_farida,
            isFavorite = false
        ),

        // Gastroenterology
        Doctor(
            id = 7,
            name = "Dr. Khelifi Rachid",
            speciality = "Gastroenterologist",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 72 18",
            email = "",
            about = "Specialist in digestive system disorders and treatments.",
            emoji = "?????",
            imageRes = R.drawable.dr_khelifi_rachid,
            isFavorite = false
        ),

        // Pediatrics
        Doctor(
            id = 8,
            name = "Dr. Amrani Lina",
            speciality = "Pediatrics",
            experience = "",
            rating = 0f,
            patients = "",
            yearsExp = "",
            phone = "+213 31 50 38 66",
            email = "",
            about = "Pediatrician providing healthcare for infants and children.",
            emoji = "?????",
            imageRes = R.drawable.dr_amrani_lina,
            isFavorite = false
        )
    )

    val specialities = listOf(
        Speciality(1, "Cardiology", 12, Color.parseColor("#FFEBEE"), R.drawable.cardiology),
        Speciality(2, "Neurology", 8, Color.parseColor("#F3E5F5"), R.drawable.neurology),
        Speciality(3, "Radiology", 11, Color.parseColor("#E3F2FD"), R.drawable.radiology),
        Speciality(4, "Dentistry", 15, Color.parseColor("#FFF8E1"), R.drawable.dentistry),
        Speciality(5, "Orthopedics", 9, Color.parseColor("#E8F5E9"), R.drawable.orthopedics),
        Speciality(6, "Ophthalmology", 7, Color.parseColor("#E0F7FA"), R.drawable.ophthamology),
        Speciality(7, "Gastroenterology", 6, Color.parseColor("#FBE9E7"), R.drawable.gastronologie),
        Speciality(8, "Pediatrics", 14, Color.parseColor("#E8EAF6"), R.drawable.pediatrics)
    )

    fun getFavoriteDoctors(): List<Doctor> = doctors.filter { it.isFavorite }

    fun getDoctorById(id: Int): Doctor? = doctors.find { it.id == id }
}
