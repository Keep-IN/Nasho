package com.core.data.repositories

import androidx.lifecycle.LiveData
import com.core.data.network.Result
import androidx.lifecycle.liveData
import com.core.data.reqres.login.DataLoginResponse
import com.core.data.reqres.login.LoginRequest
import com.core.data.reqres.login.LoginResponse
import com.core.di.ApiContractLogin
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val api: ApiContractLogin

){
    fun login(
        email: String,
        password: String
    ): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)

        val response= api.login(LoginRequest(password, email))
        val responseBody= response.body() ?: LoginResponse(DataLoginResponse(""),"")
        try {
            if (response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            } else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch(e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}