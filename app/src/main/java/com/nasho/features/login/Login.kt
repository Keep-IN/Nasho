package com.nasho.features.login
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.nasho.R
import com.nasho.databinding.ActivityLoginBinding
import com.nasho.features.Materi.MateriVideo
import com.nasho.features.signup.SignUp

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

        binding.apply {
            tilEmailLogin.editText?.doOnTextChanged { text, start, before, count ->
                val imel = text.toString()
                if (authViewModel.validateEmail(imel) || imel.isEmpty()) {
                    tilEmailLogin.isErrorEnabled = false
                } else {
                    tilEmailLogin.isErrorEnabled = true
                    btnLogin.isEnabled = false
                    tilEmailLogin.error = "Email harus sesuai format penulisan"
                }
                validateInput()
            }

            tilPasswordLogin.editText?.doOnTextChanged{text, start, before, count ->
                if(text.toString().isNotEmpty()){
                    tilPasswordLogin.isErrorEnabled = false
                } else{
                    tilPasswordLogin.isErrorEnabled = true
                    btnLogin.isEnabled = false
                    tilPasswordLogin.error = "Password tidak boleh kosong"
                }
                validateInput()
            }

            tvDaftar.setOnClickListener {
                startActivity(Intent(this@Login, MateriVideo::class.java))
            }
        }
    }
    private fun validateInput() {
        val email = binding.tilEmailLogin.editText?.text.toString()
        val password = binding.tilPasswordLogin.editText?.text.toString()

        val isEmailValid = authViewModel.validateEmail(email)
        val isPasswordValid = password.isNotEmpty()

        binding.btnLogin.isEnabled = isEmailValid && isPasswordValid
    }

}