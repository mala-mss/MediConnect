package com.mediconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediconnect.R
import com.mediconnect.data.model.Doctor

class DoctorAdapter(
    private var doctors: List<Doctor>,
    private val onItemClick: (Doctor) -> Unit,
    private val onDetailsClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        val tvDoctorName: TextView = view.findViewById(R.id.tvDoctorName)
        val tvSpeciality: TextView = view.findViewById(R.id.tvSpeciality)
        val tvDistance: TextView = view.findViewById(R.id.tvDistance)
        val btnSeeDetails: TextView = view.findViewById(R.id.btnSeeDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.ivAvatar.setImageResource(doctor.imageRes)
        holder.tvDoctorName.text = doctor.name
        holder.tvSpeciality.text = doctor.speciality
        holder.tvDistance.text = doctor.experience

        holder.itemView.setOnClickListener { onItemClick(doctor) }
        holder.btnSeeDetails.setOnClickListener { onDetailsClick(doctor) }
    }

    override fun getItemCount() = doctors.size

    fun updateDoctors(newDoctors: List<Doctor>) {
        doctors = newDoctors
        notifyDataSetChanged()
    }
}
