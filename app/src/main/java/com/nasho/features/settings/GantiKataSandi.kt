package com.nasho.features.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.core.data.network.Result
import com.nasho.R
import com.nasho.databinding.ActivityGantiKataSandiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GantiKataSandi : AppCompatActivity() {
    private lateinit var binding: ActivityGantiKataSandiBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGantiKataSandiBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        profileViewModel.getProfile().observe(this@GantiKataSandi, Observer { result ->
            when (result) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    val profileData =
                        result.data.data.firstOrNull() // Assuming the list has at least one item
                    if (profileData != null) {
                        binding.tvNamaChange.text = profileData.username
                        binding.tvEmailChange.text = profileData.email
                        Log.d("Profile Data", "Username: ${profileData.username}, Email: ${profileData.email}")
                    }
                }
                is Result.Error -> {
                    Toast.makeText(this@GantiKataSandi, result.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Check if the new password and confirm password match
        binding.tilKonfBaru.editText?.addTextChangedListener {
            validatePasswords()
        }
        binding.tilPwBaru.editText?.addTextChangedListener {
            validatePasswords()
        }

        binding.btnSimpanPw.setOnClickListener {
            val oldPassword = binding.tilPwLama.editText?.text.toString().trim()
            val newPassword = binding.tilPwBaru.editText?.text.toString().trim()
            val retypedPassword = binding.tilKonfBaru.editText?.text.toString().trim()

            if (newPassword == retypedPassword) {
                profileViewModel.updatePassword(oldPassword, newPassword, retypedPassword)
                profileViewModel.updatePasswordLiveData.observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            // Show loading indicator if needed
                        }
                        is Result.Success -> {
                            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show()

                        }
                        is Result.Error -> {
                            Toast.makeText(this, result.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePasswords() {
        val newPassword = binding.tilPwBaru.editText?.text.toString()
        val confirmPassword = binding.tilKonfBaru.editText?.text.toString()

        binding.btnSimpanPw.isEnabled = newPassword == confirmPassword && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}
