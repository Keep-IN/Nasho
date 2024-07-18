package com.nasho.features.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isNotEmpty
import androidx.core.widget.doOnTextChanged
import com.nasho.R
import com.nasho.databinding.ActivitySignUpBinding
import com.nasho.features.login.AuthViewModel
import com.nasho.features.login.Login

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val password = binding.tilPasswordDaftar.editText?.text.toString()
        val konfirm = binding.tilPasswordKonf.editText?.text.toString()
        binding.apply {
            btnDaftar.isEnabled = false

            imageView2.setOnClickListener {
                startActivity(Intent(this@SignUp, Login::class.java))
                finish()
            }

            textInputLayout2.editText?.doOnTextChanged { text, start, before, count ->
                if (textInputLayout2.isNotEmpty()) {
                    textInputLayout2.isErrorEnabled = false
                } else {
                    textInputLayout2.isErrorEnabled = true
                    textInputLayout2.error = ""
                }
                validateInput()
            }

            tilEmailDaftar.editText?.doOnTextChanged { text, start, before, count ->
                if (tilEmailDaftar.isNotEmpty()) {
                    tilEmailDaftar.isErrorEnabled = false
                } else {
                    tilEmailDaftar.isErrorEnabled = true
                    tilEmailDaftar.error = ""
                }
                validateInput()
            }

            tilPasswordDaftar.editText?.doOnTextChanged { text, start, before, count ->
                val password = text.toString()

                if (password.length >= 8 && authViewModel.validatePassword(password) && authViewModel.pwDigits(password)) {
                    tilPasswordDaftar.isErrorEnabled = false
                } else {
                    tilPasswordDaftar.isErrorEnabled = true
                    if (password.isNotEmpty() && password.length < 8) {
                        tilPasswordDaftar.error = "Kata sandi harus minimal 8 karakter"
                    } else {
                        tilPasswordDaftar.error = "Kata sandi harus terdiri dari huruf dan karakter"
                    }
                }
                validateInput()
            }

            tilPasswordKonf.editText?.doOnTextChanged{ text, start, before, count ->
                if(konfirm == password) {
                    tilPasswordKonf.isErrorEnabled = false
                } else {
                    tilPasswordKonf.isErrorEnabled = true
                    tilPasswordKonf.error = "Kata sandi tidak sama"
                }
                validateInput()
            }

            btnDaftar.setOnClickListener{
                startActivity(Intent(this@SignUp,Login::class.java))
                finish()
            }
        }
    }

    private fun validateInput() {
        val email = binding.tilEmailDaftar.editText?.text.toString()
        val nama = binding.textInputLayout2.editText?.text.toString()
        val password = binding.tilPasswordDaftar.editText?.text.toString()
        val konfirm = binding.tilPasswordKonf.editText?.text.toString()

        val isEmailValid = authViewModel.validateEmail(email)
        val isNamaValid = nama.isNotEmpty()
        val isPasswordValid = authViewModel.validatePassword(password)
        val isKonfirmValid = konfirm == password

        binding.btnDaftar.isEnabled = isEmailValid && isNamaValid && isPasswordValid && isKonfirmValid
    }
}