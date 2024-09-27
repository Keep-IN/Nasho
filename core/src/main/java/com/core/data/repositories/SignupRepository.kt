package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.signup.SignupRequest
import com.core.data.reqres.signup.SignupResponse
import com.core.di.ApiContractSignup
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignupRepository @Inject constructor(
    private val api: ApiContractSignup
) {
    fun postSignup(
        password: String,
        retypedPassword: String,
        email: String,
        username: String
    ): LiveData<Result<SignupResponse>> = liveData {
        emit(Result.Loading)
        val response = api.signup(SignupRequest(password, retypedPassword, email, username))
        val responseBody = response.body()
        try {
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }
}