package com.mediconnect.data.model

import android.graphics.Color

object SampleData {

    val doctors = mutableListOf(
        Doctor(
            id = 1,
            name = "Dr. Sarah Ahmed",
            speciality = "Cardiologist",
            experience = "8 yrs exp",
            rating = 4.9f,
            patients = "1.2k",
            yearsExp = "8+",
            phone = "+1 234 567 8901",
            email = "sarah@mediconnect.com",
            about = "Dr. Sarah Ahmed is a board-certified cardiologist with 8+ years of experience. Specializing in cardiovascular diseases and preventive cardiology.",
            emoji = "👩‍⚕️",
            isFavorite = true
        ),
        Doctor(
            id = 2,
            name = "Dr. Omar Hassan",
            speciality = "Neurologist",
            experience = "12 yrs exp",
            rating = 4.8f,
            patients = "2.1k",
            yearsExp = "12+",
            phone = "+1 234 567 8902",
            email = "omar@mediconnect.com",
            about = "Dr. Omar Hassan is a renowned neurologist specializing in brain disorders, epilepsy, and stroke management with over 12 years in practice.",
            emoji = "👨‍⚕️",
            isFavorite = false
        ),
        Doctor(
            id = 3,
            name = "Dr. Lina Khalid",
            speciality = "Dentist",
            experience = "5 yrs exp",
            rating = 4.7f,
            patients = "800",
            yearsExp = "5+",
            phone = "+1 234 567 8903",
            email = "lina@mediconnect.com",
            about = "Dr. Lina Khalid is a skilled dentist specializing in cosmetic dentistry and orthodontics, helping patients achieve beautiful, healthy smiles.",
            emoji = "👩‍⚕️",
            isFavorite = true
        ),
        Doctor(
            id = 4,
            name = "Dr. Karim Nour",
            speciality = "Orthopedic",
            experience = "10 yrs exp",
            rating = 4.6f,
            patients = "1.5k",
            yearsExp = "10+",
            phone = "+1 234 567 8904",
            email = "karim@mediconnect.com",
            about = "Dr. Karim Nour specializes in orthopedic surgery and sports medicine, treating bone, joint, and muscle conditions.",
            emoji = "👨‍⚕️",
            isFavorite = false
        ),
        Doctor(
            id = 5,
            name = "Dr. Moussa Belaid",
            speciality = "Dermatologist",
            experience = "7 yrs exp",
            rating = 4.5f,
            patients = "900",
            yearsExp = "7+",
            phone = "+1 234 567 8905",
            email = "moussa@mediconnect.com",
            about = "Dr. Moussa Belaid is an experienced dermatologist treating skin conditions from acne to complex dermatological disorders.",
            emoji = "👨‍⚕️",
            isFavorite = false
        ),
        Doctor(
            id = 6,
            name = "Dr. Bensam Ali",
            speciality = "Cardiologist",
            experience = "15 yrs exp",
            rating = 4.9f,
            patients = "3k",
            yearsExp = "15+",
            phone = "+1 234 567 8906",
            email = "bensam@mediconnect.com",
            about = "Dr. Bensam Ali is a senior cardiologist with extensive expertise in interventional cardiology and heart failure management.",
            emoji = "👨‍⚕️",
            isFavorite = false
        )
    )

    val specialities = listOf(
        Speciality(1, "Cardiology", 12, "❤️", Color.parseColor("#FFEBEE")),
        Speciality(2, "Neurology", 8, "🧠", Color.parseColor("#F3E5F5")),
        Speciality(3, "Radiology", 11, "🩻", Color.parseColor("#E3F2FD")),
        Speciality(4, "Dentistry", 15, "🦷", Color.parseColor("#FFF8E1")),
        Speciality(5, "Orthopedics", 9, "🦴", Color.parseColor("#E8F5E9")),
        Speciality(6, "Ophthalmology", 7, "👁️", Color.parseColor("#E0F7FA")),
        Speciality(7, "Gastroenterology", 6, "🫀", Color.parseColor("#FBE9E7")),
        Speciality(8, "Pediatrics", 14, "👶", Color.parseColor("#E8EAF6"))
    )

    fun getFavoriteDoctors(): List<Doctor> = doctors.filter { it.isFavorite }

    fun getDoctorById(id: Int): Doctor? = doctors.find { it.id == id }
}
