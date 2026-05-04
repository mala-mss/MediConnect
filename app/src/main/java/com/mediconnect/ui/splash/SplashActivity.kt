package com.mediconnect.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.mediconnect.databinding.ActivitySplashBinding
import com.mediconnect.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAnimations()
        setupClickListeners()
    }

    private fun setupAnimations() {
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

        // Bubbly animations for the background circles
        startBubbleAnimation(binding.bubble1, 3000L, 0f, 30f)
        startBubbleAnimation(binding.bubble2, 2500L, 0f, -20f)
        startBubbleAnimation(binding.bubble3, 3500L, 20f, 0f)
        startBubbleAnimation(binding.bubble4, 2000L, -15f, 15f)

        // Animate button up
        val slideUp = TranslateAnimation(0f, 0f, 100f, 0f).apply {
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
    }

    private fun startBubbleAnimation(view: android.view.View, duration: Long, tx: Float, ty: Float) {
        val anim = TranslateAnimation(0f, tx, 0f, ty).apply {
            this.duration = duration
            repeatMode = Animation.REVERSE
            repeatCount = Animation.INFINITE
        }
        view.startAnimation(anim)
    }

    private fun setupClickListeners() {
        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
