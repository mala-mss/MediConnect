package com.mediconnect.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.mediconnect.R
import com.mediconnect.databinding.ActivitySplashBinding
import com.mediconnect.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Animate logo in
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 800 }
        val scaleIn = ScaleAnimation(
            0.8f, 1f, 0.8f, 1f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        ).apply { duration = 800 }

        val animSet = AnimationSet(true).apply {
            addAnimation(fadeIn)
            addAnimation(scaleIn)
        }
        binding.brandLayout.startAnimation(animSet)

        // Animate button up
        val slideUp = android.view.animation.TranslateAnimation(0f, 0f, 100f, 0f).apply {
            duration = 600
            startOffset = 400
            fillAfter = true
        }
        val btnFade = AlphaAnimation(0f, 1f).apply {
            duration = 600
            startOffset = 400
            fillAfter = true
        }
        val btnAnim = AnimationSet(true).apply {
            addAnimation(slideUp)
            addAnimation(btnFade)
        }
        binding.btnGetStarted.startAnimation(btnAnim)

        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
