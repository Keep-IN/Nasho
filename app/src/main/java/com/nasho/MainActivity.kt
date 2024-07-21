package com.nasho

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.databinding.ActivityMainBinding
import com.nasho.features.home.Home
import com.nasho.features.login.Login
import com.nasho.features.settings.Pengaturan

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val accessToken = sharedPref.getString("token", null)

        if (accessToken != null) {
            val intent = Intent(this, Pengaturan::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}
