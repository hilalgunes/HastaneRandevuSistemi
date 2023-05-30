package com.example.hastanerandevusistemi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.hastanerandevusistemi.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashActivityViewModel: SplashActivityViewModel by viewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)

        if(splashActivityViewModel.firtOpen()) {
            getData()
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 1000)
      } else {
          val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 1000)

        }
    }

    private fun getData() {
        splashActivityViewModel.setCityData()
        splashActivityViewModel.setDistrictData()
        splashActivityViewModel.setHastaneData()
        splashActivityViewModel.setPoliklinikData()
        splashActivityViewModel.setDoktorData()
        splashActivityViewModel.setGunData()
        splashActivityViewModel.setSaatData()
    }
}