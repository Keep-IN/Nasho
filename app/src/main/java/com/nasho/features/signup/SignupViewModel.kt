package com.nasho.features.signup

import androidx.lifecycle.ViewModel
import com.core.data.repositories.SignupRepository
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    private val repository: SignupRepository
) : ViewModel()
{
    var isEmailValid = false
    var isPasswordValid = false

    fun validateEmail(email: String): Boolean{
        isEmailValid = email.contains("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
        return isEmailValid
    }

    fun validatePassword(password: String): Boolean {
        isPasswordValid = password.contains("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}\$".toRegex())
        return isPasswordValid
    }

    fun pwDigits(password: String): Boolean {
        return password.length >= 8
    }

    fun postSignup(
        password: String,
        retypedPassword: String,
        email: String,
        username: String
    )= repository.postSignup(password, retypedPassword, email, username)
}