package com.mediconnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediconnect.R
import com.mediconnect.data.model.Speciality

class SpecialityAdapter(
    private var specialities: List<Speciality>,
    private val onItemClick: (Speciality) -> Unit
) : RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder>() {

    inner class SpecialityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIcon: TextView = view.findViewById(R.id.tvIcon)
        val tvName: TextView = view.findViewById(R.id.tvSpecialityName)
        val tvCount: TextView = view.findViewById(R.id.tvDoctorCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_speciality, parent, false)
        return SpecialityViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialityViewHolder, position: Int) {
        val speciality = specialities[position]
        holder.tvIcon.text = speciality.emoji
        holder.tvIcon.setBackgroundColor(speciality.bgColor)
        holder.tvName.text = speciality.name
        holder.tvCount.text = "${speciality.doctorCount} doctors"
        holder.itemView.setOnClickListener { onItemClick(speciality) }
    }

    override fun getItemCount() = specialities.size

    fun filter(query: String) {
        // Filtering handled by activity
    }
}
