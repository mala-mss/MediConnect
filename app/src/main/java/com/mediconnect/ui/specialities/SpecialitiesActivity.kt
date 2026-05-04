package com.mediconnect.ui.specialities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mediconnect.R
import com.mediconnect.adapter.SpecialityAdapter
import com.mediconnect.data.model.SampleData
import com.mediconnect.data.model.Speciality
import com.mediconnect.databinding.ActivitySpecialitiesBinding
import com.mediconnect.ui.categories.CategoriesActivity
import com.mediconnect.ui.favorites.FavoritesActivity
import com.mediconnect.ui.home.HomeActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SpecialitiesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecialitiesBinding
    private lateinit var specialityAdapter: SpecialityAdapter
    private val allSpecialities = SampleData.specialities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpecialitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        setupRecyclerView()
        setupSearch()
        setupClickListeners()
    }

    private fun setupBottomNavigation() {
        // Highlight Search
        binding.includeNavigation.ivSearch.setColorFilter(getColor(R.color.primary_green))
        binding.includeNavigation.tvSearch.setTextColor(getColor(R.color.primary_green))
        
        // Others grey
        binding.includeNavigation.ivHome.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvHome.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivFavorite.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvFavorite.setTextColor(getColor(R.color.text_light))
        binding.includeNavigation.ivProfile.setColorFilter(getColor(R.color.text_light))
        binding.includeNavigation.tvProfile.setTextColor(getColor(R.color.text_light))
    }

    private fun setupRecyclerView() {
        specialityAdapter = SpecialityAdapter(
            specialities = allSpecialities,
            onItemClick = { speciality -> onSpecialityClick(speciality) }
        )

        binding.rvSpecialities.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = specialityAdapter
        }
    }

    private fun setupSearch() {
        binding.etSearchSpeciality.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim().lowercase()
                val filtered = if (query.isEmpty()) {
                    allSpecialities
                } else {
                    allSpecialities.filter { it.name.lowercase().contains(query) }
                }
                specialityAdapter = SpecialityAdapter(filtered) { onSpecialityClick(it) }
                binding.rvSpecialities.adapter = specialityAdapter
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }


    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.includeNavigation.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.includeNavigation.navFavorite.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun onSpecialityClick(speciality: Speciality) {
        val intent = Intent(this, CategoriesActivity::class.java).apply {
            putExtra("FILTER_SPECIALITY", speciality.name)
        }
        startActivity(intent)
    }
}
