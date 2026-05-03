package com.mediconnect.ui.doctor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mediconnect.data.model.SampleData
import com.mediconnect.databinding.ActivityDoctorDetailsBinding
import com.mediconnect.ui.favorites.FavoritesActivity
import com.mediconnect.ui.home.HomeActivity
import com.mediconnect.ui.specialities.SpecialitiesActivity

class DoctorDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorDetailsBinding

    companion object {
        const val EXTRA_DOCTOR_ID = "extra_doctor_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctorId = intent.getIntExtra(EXTRA_DOCTOR_ID, 1)
        val doctor = SampleData.getDoctorById(doctorId)

        doctor?.let { doc ->
            // Populate UI
            binding.tvDoctorAvatar.text = doc.emoji
            binding.tvDoctorName.text = doc.name
            binding.tvSpeciality.text = doc.speciality
            binding.tvYearsExp.text = doc.yearsExp
            binding.tvPatients.text = doc.patients
            binding.tvRating.text = doc.rating.toString()
            binding.tvAbout.text = doc.about

            // Favorite button state
            updateFavoriteIcon(doc.isFavorite)

            binding.btnFavorite.setOnClickListener {
                doc.isFavorite = !doc.isFavorite
                updateFavoriteIcon(doc.isFavorite)
                val msg = if (doc.isFavorite) "Added to favorites" else "Removed from favorites"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }

            binding.btnCall.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${doc.phone}")
                }
                startActivity(intent)
            }

            binding.btnEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${doc.email}")
                    putExtra(Intent.EXTRA_SUBJECT, "Appointment Request")
                }
                startActivity(intent)
            }

            binding.btnFacebook.setOnClickListener {
                Toast.makeText(this, "Opening Facebook profile…", Toast.LENGTH_SHORT).show()
            }
        }

        setupClickListeners()
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        binding.btnFavorite.alpha = if (isFavorite) 1.0f else 0.4f
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener { finish() }
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
}
