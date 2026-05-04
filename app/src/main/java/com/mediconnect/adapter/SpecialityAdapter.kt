package com.mediconnect.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediconnect.R
import com.mediconnect.data.model.Speciality

import android.widget.ImageView

import android.view.animation.Animation
import android.view.animation.TranslateAnimation

class SpecialityAdapter(
    private var specialities: List<Speciality>,
    private val onItemClick: (Speciality) -> Unit
) : RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder>() {

    inner class SpecialityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val iconContainer: FrameLayout = view.findViewById(R.id.iconContainer)
        val tvName: TextView = view.findViewById(R.id.tvSpecialityName)
        val contentLayout: LinearLayout = view.findViewById(R.id.contentLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_speciality, parent, false)
        return SpecialityViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialityViewHolder, position: Int) {
        val speciality = specialities[position]
        
        if (speciality.iconRes != 0) {
            holder.ivIcon.setImageResource(speciality.iconRes)
        }
        
        // Background for icons with speciality specific colors
        val shape = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = floatArrayOf(
                holder.itemView.context.resources.getDimension(R.dimen.item_radius), holder.itemView.context.resources.getDimension(R.dimen.item_radius), 
                holder.itemView.context.resources.getDimension(R.dimen.item_radius), holder.itemView.context.resources.getDimension(R.dimen.item_radius), 
                0f, 0f, 0f, 0f
            )
            setColor(speciality.bgColor)
        }
        holder.iconContainer.background = shape
        
        holder.tvName.text = speciality.name

        // Natural Masonry: Vary the height significantly
        val params = holder.iconContainer.layoutParams
        params.height = when (position % 3) {
            0 -> 160.dpToPx(holder.itemView.context)
            1 -> 220.dpToPx(holder.itemView.context)
            else -> 130.dpToPx(holder.itemView.context)
        }
        holder.iconContainer.layoutParams = params

        // Add Bubbly Animation to the icon
        startBubblyAnimation(holder.ivIcon, position)

        holder.itemView.setOnClickListener { onItemClick(speciality) }
    }

    private fun startBubblyAnimation(view: View, position: Int) {
        // Vary values slightly based on position to make icons move differently
        val duration = 2000L + (position % 3) * 500L
        val ty = -15f - (position % 2) * 10f
        
        val anim = TranslateAnimation(0f, 0f, 0f, ty).apply {
            this.duration = duration
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
            // Offset start time so they don't all jump at once
            startOffset = (position * 100L) % 1000
        }
        view.startAnimation(anim)
    }

    private fun Int.dpToPx(context: android.content.Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

    override fun getItemCount() = specialities.size
}
