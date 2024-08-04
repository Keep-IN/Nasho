package com.core.di

import com.core.data.reqres.signup.SignupRequest
import com.core.data.reqres.signup.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiContractSignup {
    @POST("register")
    suspend fun signup(
        @Body signupRequest: SignupRequest
    ): Response<SignupResponse>

}