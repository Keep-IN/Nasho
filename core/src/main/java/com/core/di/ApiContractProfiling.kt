package com.core.di

import com.core.data.reqres.editpassword.EditPasswordRequest
import com.core.data.reqres.editpassword.EditPasswordResponse
import com.core.data.reqres.editprofile.EditProfileReques
import com.core.data.reqres.editprofile.EditProfileResponse
import com.core.data.reqres.profiling.GetProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ApiContractProfiling {
    @GET("/profile")
    suspend fun  getProfile(): Response <GetProfileResponse>

    @PATCH("/profile")
    suspend fun updateProfile(
        @Body response: EditProfileReques
    ): Response<EditProfileResponse>

    @PATCH("/profile/newpassword")
    suspend fun updatePassword(
        @Body response: EditPasswordRequest
    ): Response<EditPasswordResponse>

}