package com.mediconnect.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediconnect.adapter.DoctorAdapter
import com.mediconnect.data.model.Doctor
import com.mediconnect.data.model.SampleData
import com.mediconnect.databinding.ActivityHomeBinding
import com.mediconnect.ui.categories.CategoriesActivity
import com.mediconnect.ui.doctor.DoctorDetailsActivity
import com.mediconnect.ui.favorites.FavoritesActivity
import com.mediconnect.ui.specialities.SpecialitiesActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var doctorAdapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
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

        binding.chipCardiologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Cardiologist" }
            doctorAdapter.updateDoctors(filtered)
        }

        binding.chipDermatologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Dermatologist" }
            doctorAdapter.updateDoctors(filtered)
        }

        binding.chipNeurologist.setOnClickListener {
            val filtered = SampleData.doctors.filter { it.speciality == "Neurologist" }
            doctorAdapter.updateDoctors(filtered)
        }

        // Bottom nav
        binding.navSearch.setOnClickListener {
            startActivity(Intent(this, SpecialitiesActivity::class.java))
        }

        binding.navFavorite.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
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
