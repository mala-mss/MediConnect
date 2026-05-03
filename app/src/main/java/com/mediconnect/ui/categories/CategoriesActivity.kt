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

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var doctorAdapter: DoctorAdapter
    private var allDoctors = SampleData.doctors.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
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
        }

        binding.chipCardiologist.setOnClickListener {
            filterBy("Cardiologist")
        }

        binding.chipNeurologist.setOnClickListener {
            filterBy("Neurologist")
        }

        binding.chipDentist.setOnClickListener {
            filterBy("Dentist")
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

