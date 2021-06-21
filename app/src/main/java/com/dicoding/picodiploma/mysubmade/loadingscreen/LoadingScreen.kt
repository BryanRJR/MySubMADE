package com.dicoding.picodiploma.mysubmade.loadingscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.dicoding.picodiploma.mysubmade.MainActivity
import com.dicoding.picodiploma.mysubmade.databinding.ActivitySplashScreenBinding

class LoadingScreen : AppCompatActivity() {

    private val delay: Int = 2000
    private lateinit var loadingBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(loadingBinding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler(mainLooper).postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }, delay.toLong())
    }
}