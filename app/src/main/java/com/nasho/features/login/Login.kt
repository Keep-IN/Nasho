package com.nasho.features.login

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
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.core.data.network.Result
import com.nasho.R
import com.nasho.databinding.ActivityLoginBinding
import com.nasho.features.home.Home
import com.nasho.features.signup.SignUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this@Login)
        val sPref = sharedPref.edit()
        sPref.clear()
        sPref.apply()

        binding.apply {
            tilEmailLogin.editText?.doOnTextChanged { text, _, _, _ ->
                val email = text.toString()
                if (authViewModel.validateEmail(email) || email.isEmpty()) {
                    tilEmailLogin.isErrorEnabled = false
                } else {
                    tilEmailLogin.isErrorEnabled = true
                    tilEmailLogin.error = "Email harus sesuai format penulisan"
                }
                validateInput()
            }

            tilPasswordLogin.editText?.doOnTextChanged { text, _, _, _ ->
                val password = text.toString()
                if (authViewModel.validatePassword(password) || password.isEmpty()) {
                    tilPasswordLogin.isErrorEnabled = false
                } else {
                    tilPasswordLogin.isErrorEnabled = true
                    tilPasswordLogin.error = "Password harus mengandung huruf dan angka, dan minimal 8 karakter"
                }
                validateInput()
            }

            btnLogin.setOnClickListener {
                val email = tilEmailLogin.editText?.text.toString().trim()
                val password = tilPasswordLogin.editText?.text.toString().trim()
                authViewModel.login(email, password).observe(this@Login, Observer { result ->
                    when (result) {
                        is Result.Loading -> {
                            // Show loading indicator
                        }
                        is Result.Success -> {
                            sPref.putString("token", result.data.data.accessToken)
                            Log.d("Token", "token: ${result.data.data.accessToken}")
                            sPref.apply()

                            Toast.makeText(this@Login, "Login successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Login, Home::class.java)
                            startActivity(intent)
                            finish()
                        }
                        is Result.Error -> {

                            Toast.makeText(this@Login, result.errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

           tvDaftar.setOnClickListener {
                startActivity(Intent(this@Login, SignUp::class.java))
            }
        }
    }

    private fun validateInput() {
        val email = binding.tilEmailLogin.editText?.text.toString()
        val password = binding.tilPasswordLogin.editText?.text.toString()

        val isEmailValid = authViewModel.validateEmail(email)
        val isPasswordValid = authViewModel.validatePassword(password)

        binding.btnLogin.isEnabled = isEmailValid && isPasswordValid
    }
}
