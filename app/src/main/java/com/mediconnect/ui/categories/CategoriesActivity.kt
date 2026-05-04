package com.mediconnect.ui.categories

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediconnect.adapter.DoctorAdapter
import com.mediconnect.data.model.Doctor
import com.mediconnect.data.model.SampleData
import com.mediconnect.databinding.ActivityCategoriesBinding
import com.mediconnect.ui.doctor.DoctorDetailsActivity
import com.mediconnect.ui.favorites.FavoritesActivity
import com.mediconnect.ui.home.HomeActivity
import com.mediconnect.ui.specialities.SpecialitiesActivity

import com.mediconnect.R
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private var allDoctors = SampleData.doctors.toList()
    private var chipList = listOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChipList()
        setupRecyclerView()
        setupClickListeners()
        
        // Default select "All"
        selectChip(binding.chipAll, ContextCompat.getColor(this, R.color.primary_green))
    }

    private fun setupChipList() {
        chipList = listOf(
            binding.chipAll,
            binding.chipCardiologist,
            binding.chipNeurologist,
            binding.chipRadiologist,
            binding.chipDentist,
            binding.chipOrthopedic,
            binding.chipOphthalmologist,
            binding.chipGastroenterologist,
            binding.chipPediatrics
        )
    }

    private fun setupRecyclerView() {
        doctorAdapter = DoctorAdapter(
            doctors = allDoctors,
            onItemClick = { doctor -> openDoctorDetails(doctor) },
            onDetailsClick = { doctor -> openDoctorDetails(doctor) }
        )

        binding.rvDoctors.apply {
            layoutManager = LinearLayoutManager(this@CategoriesActivity)
            adapter = doctorAdapter
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.chipAll.setOnClickListener {
            doctorAdapter.updateDoctors(allDoctors)
            selectChip(it as TextView, ContextCompat.getColor(this, R.color.primary_green))
        }

        binding.chipCardiologist.setOnClickListener {
            filterBy("Cardiologist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Cardiology" }?.bgColor ?: 0)
        }

        binding.chipNeurologist.setOnClickListener {
            filterBy("Neurologist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Neurology" }?.bgColor ?: 0)
        }

        binding.chipRadiologist.setOnClickListener {
            filterBy("Radiologist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Radiology" }?.bgColor ?: 0)
        }

        binding.chipDentist.setOnClickListener {
            filterBy("Dentist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Dentistry" }?.bgColor ?: 0)
        }

        binding.chipOrthopedic.setOnClickListener {
            filterBy("Orthopedic")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Orthopedics" }?.bgColor ?: 0)
        }

        binding.chipOphthalmologist.setOnClickListener {
            filterBy("Ophthalmologist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Ophthalmology" }?.bgColor ?: 0)
        }

        binding.chipGastroenterologist.setOnClickListener {
            filterBy("Gastroenterologist")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Gastroenterology" }?.bgColor ?: 0)
        }

        binding.chipPediatrics.setOnClickListener {
            filterBy("Pediatrics")
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Pediatrics" }?.bgColor ?: 0)
        }

        // Bottom nav
        binding.includeNavigation.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.includeNavigation.navSearch.setOnClickListener {
            startActivity(Intent(this, SpecialitiesActivity::class.java))
        }

        binding.includeNavigation.navFavorite.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun selectChip(selectedChip: TextView, color: Int) {
        // Reset all chips
        chipList.forEach { chip ->
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 30f * resources.displayMetrics.density
                setColor(ContextCompat.getColor(this@CategoriesActivity, R.color.background))
                setStroke(1, ContextCompat.getColor(this@CategoriesActivity, R.color.text_light))
            }
            chip.background = drawable
            chip.setTextColor(ContextCompat.getColor(this, R.color.text_light))
        }

        // Highlight selected chip
        val selectedDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 30f * resources.displayMetrics.density
            setColor(color)
        }
        selectedChip.background = selectedDrawable
        
        // Set text color (white for better contrast if background is colored)
        selectedChip.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun filterBy(speciality: String) {
        val filtered = allDoctors.filter { it.speciality == speciality }
        doctorAdapter.updateDoctors(filtered)
    }

    private fun openDoctorDetails(doctor: Doctor) {
        val intent = Intent(this, DoctorDetailsActivity::class.java).apply {
            putExtra(DoctorDetailsActivity.EXTRA_DOCTOR_ID, doctor.id)
        }
        startActivity(intent)
    }
}

