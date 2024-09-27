package com.nasho.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.core.data.network.Result
import com.core.data.repositories.LoginRepository
import com.core.data.reqres.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> {
        return loginRepository.login(email, password)
    }

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


}
