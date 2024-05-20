package com.test.anotatudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.anotatudo.databinding.ActivitySplashBinding
import com.test.anotatudo.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)


            binding.btnNextHome.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

}