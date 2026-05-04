package com.mediconnect.ui.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mediconnect.R
import com.mediconnect.adapter.DoctorAdapter
import com.mediconnect.data.model.Doctor
import com.mediconnect.data.model.SampleData
import com.mediconnect.databinding.ActivityFavoritesBinding
import com.mediconnect.ui.doctor.DoctorDetailsActivity
import com.mediconnect.ui.home.HomeActivity
import com.mediconnect.ui.specialities.SpecialitiesActivity

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var favAdapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupBottomNavigation() {
        // Highlight Favorite
        binding.includeNavigation.ivFavorite.setColorFilter(getColor(R.color.primary_green))
        binding.includeNavigation.tvFavorite.setTextColor(getColor(R.color.primary_green))
        
        // Others grey
        binding.includeNavigation.ivHome.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvHome.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivSearch.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvSearch.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivProfile.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvProfile.setTextColor(getColor(R.color.text_light))
    }

    private fun setupRecyclerView() {
        favAdapter = DoctorAdapter(
            doctors = SampleData.getFavoriteDoctors(),
            onItemClick = { doctor -> openDoctorDetails(doctor) },
            onDetailsClick = { doctor -> openDoctorDetails(doctor) }
        )

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = favAdapter
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.includeNavigation.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.includeNavigation.navSearch.setOnClickListener {
            startActivity(Intent(this, SpecialitiesActivity::class.java))
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
        // Refresh favorites list when returning from doctor details
        favAdapter.updateDoctors(SampleData.getFavoriteDoctors())
    }
}
