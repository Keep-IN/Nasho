package com.nasho.features.settings

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.nasho.R
import com.core.data.network.Result
import com.core.data.reqres.profiling.GetProfileResponse
import com.nasho.databinding.ActivityLoginBinding
import com.nasho.databinding.ActivityPengaturanBinding
import com.nasho.features.home_materi.Home
import com.nasho.features.signup.SignUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pengaturan : AppCompatActivity() {
    private lateinit var binding: ActivityPengaturanBinding
    private val profileViewModel: ProfileViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            cvUbahProfile.setOnClickListener{
                startActivity(Intent(this@Pengaturan, UbahProfil::class.java))
            }
            cvGantiSandi.setOnClickListener{
                startActivity(Intent(this@Pengaturan, GantiKataSandi::class.java))
            }
            cvTentangKami.setOnClickListener{
                startActivity(Intent(this@Pengaturan, TentangKami::class.java))
            }
            cvKeluarAkun.setOnClickListener{
                val dialog = AlertLogout()
                dialog.show(supportFragmentManager, "AlertLogout")
            }
            ivBackSet.setOnClickListener {
                startActivity(Intent(this@Pengaturan, Home::class.java))
            }
        }
        profileViewModel.getProfile().observe(this@Pengaturan, Observer { result ->
            when (result) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    val profileData =
                        result.data.data.firstOrNull() // Assuming the list has at least one item
                    if (profileData != null) {
                        binding.tvNamaUser.text = profileData.username
                        binding.tvEmailUser.text = profileData.email
                        Log.d("Profile Data", "Username: ${profileData.username}, Email: ${profileData.email}")
                    }
                }
                is Result.Error -> {
                        Toast.makeText(this@Pengaturan, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}