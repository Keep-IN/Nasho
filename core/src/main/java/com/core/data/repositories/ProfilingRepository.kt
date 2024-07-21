package com.core.data.repositories

import com.core.data.network.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.reqres.editpassword.EditPasswordRequest
import com.core.data.reqres.editpassword.EditPasswordResponse
import com.core.data.reqres.editprofile.EditProfileReques
import com.core.data.reqres.editprofile.EditProfileResponse
import com.core.data.reqres.profiling.GetProfileResponse
import com.core.di.ApiContractProfiling
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilingRepository @Inject constructor(
    private val api: ApiContractProfiling
) {
    fun getProfile(): LiveData<Result<GetProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = api.getProfile()
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMassage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMassage))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun updateProfile(username: String): LiveData<Result<EditProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val request = EditProfileReques(username)
            val response = api.updateProfile(request)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }

    fun updatePassword(
        password: String,
        retypedPassword: String,
        oldPassword: String
    ): LiveData<Result<EditPasswordResponse>> = liveData {
        emit(Result.Loading)
        try {
            val request = EditPasswordRequest(
                password = password,
                retypedPassword = retypedPassword,
                oldPassword = oldPassword
            )
            val response = api.updatePassword(request)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown error occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            e.message?.let { Result.Error(it) }?.let { emit(it) }
        }
    }
}