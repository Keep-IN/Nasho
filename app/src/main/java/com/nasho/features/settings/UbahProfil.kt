package com.nasho.features.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.R
import com.nasho.databinding.ActivityUbahProfilBinding
import com.core.data.network.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UbahProfil : AppCompatActivity() {
    private lateinit var binding: ActivityUbahProfilBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahProfilBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.etNama.setOnTouchListener { v, event ->
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                val drawableEnd = 2
                if (event.rawX >= (binding.etNama.right - binding.etNama.compoundDrawables[drawableEnd].bounds.width())) {
                    binding.etNama.text?.clear()
                    return@setOnTouchListener true
                }
            }
            false
        }

        profileViewModel.getProfile().observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    // Handle loading state
                }
                is Result.Success -> {
                    val profileData = result.data.data.firstOrNull()
                    if (profileData != null) {
                        binding.tilNamaEdit.editText?.setText(profileData.username)
                        binding.textInputLayout3.editText?.setText(profileData.email)
                    }
                }
                is Result.Error -> {
                    Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.etNama.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateUsername()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSimpanEdit.setOnClickListener {
            val newUsername = binding.tilNamaEdit.editText?.text.toString().trim()
            if (newUsername.isNotEmpty()) {
                profileViewModel.updateProfile(newUsername)
                profileViewModel.updateProfileLiveData.observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            // Handle loading state
                        }
                        is Result.Success -> {
                            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Pengaturan::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        is Result.Error -> {
                            Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateUsername() {
        val username = binding.etNama.text.toString().trim()
        val isValid = username.length in 6..10
        binding.btnSimpanEdit.isEnabled = isValid
    }
}
