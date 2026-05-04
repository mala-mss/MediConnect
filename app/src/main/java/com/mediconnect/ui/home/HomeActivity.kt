package com.mediconnect.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediconnect.R
import com.mediconnect.adapter.DoctorAdapter
import com.mediconnect.data.model.Doctor
import com.mediconnect.data.model.SampleData
import com.mediconnect.databinding.ActivityHomeBinding
import com.mediconnect.ui.categories.CategoriesActivity
import com.mediconnect.ui.doctor.DoctorDetailsActivity
import com.mediconnect.ui.favorites.FavoritesActivity
import com.mediconnect.ui.specialities.SpecialitiesActivity

import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private var chipList = listOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChipList()
        setupBottomNavigation()
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

    private fun setupBottomNavigation() {
        // Highlight Home
        binding.includeNavigation.ivHome.setColorFilter(getColor(R.color.primary_green))
        binding.includeNavigation.tvHome.setTextColor(getColor(R.color.primary_green))
        
        // Ensure others are grey (text_light)
        binding.includeNavigation.ivSearch.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvSearch.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivFavorite.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvFavorite.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivProfile.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvProfile.setTextColor(getColor(R.color.text_light))
    }

    private fun setupRecyclerView() {
        doctorAdapter = DoctorAdapter(
            doctors = SampleData.doctors,
            onItemClick = { doctor -> openDoctorDetails(doctor) },
            onDetailsClick = { doctor -> openDoctorDetails(doctor) }
        )

        binding.rvDoctors.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = doctorAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupClickListeners() {
        binding.tvSeeAllCategories.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        binding.tvViewAll.setOnClickListener {
            startActivity(Intent(this, CategoriesActivity::class.java))
        }

        binding.chipAll.setOnClickListener {
            doctorAdapter.updateDoctors(SampleData.doctors)
            selectChip(it as TextView, ContextCompat.getColor(this, R.color.primary_green))
        }

        binding.chipCardiologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Cardiologist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Cardiology" }?.bgColor ?: 0)
        }

        binding.chipNeurologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Neurologist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Neurology" }?.bgColor ?: 0)
        }

        binding.chipRadiologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Radiologist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Radiology" }?.bgColor ?: 0)
        }

        binding.chipDentist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Dentist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Dentistry" }?.bgColor ?: 0)
        }

        binding.chipOrthopedic.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Orthopedic" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Orthopedics" }?.bgColor ?: 0)
        }

        binding.chipOphthalmologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Ophthalmologist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Ophthalmology" }?.bgColor ?: 0)
        }

        binding.chipGastroenterologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Gastroenterologist" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Gastroenterology" }?.bgColor ?: 0)
        }

        binding.chipPediatrics.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Pediatrics" }
            doctorAdapter.updateDoctors(filtered)
            selectChip(it as TextView, SampleData.specialities.find { s -> s.name == "Pediatrics" }?.bgColor ?: 0)
        }

        // Bottom nav
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
                cornerRadius = 20f * resources.displayMetrics.density
                setColor(ContextCompat.getColor(this@HomeActivity, R.color.white))
                setStroke(1, ContextCompat.getColor(this@HomeActivity, R.color.divider))
            }
            chip.background = drawable
            chip.setTextColor(ContextCompat.getColor(this, R.color.text_medium))
        }

        // Highlight selected chip
        val selectedDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 20f * resources.displayMetrics.density
            setColor(color)
        }
        selectedChip.background = selectedDrawable
        selectedChip.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun openDoctorDetails(doctor: Doctor) {
        val intent = Intent(this, DoctorDetailsActivity::class.java).apply {
            putExtra(DoctorDetailsActivity.EXTRA_DOCTOR_ID, doctor.id)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        doctorAdapter.updateDoctors(SampleData.doctors)
    }
}
