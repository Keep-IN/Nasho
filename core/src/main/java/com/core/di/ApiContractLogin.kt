package com.core.di

import com.core.data.reqres.login.LoginRequest
import com.core.data.reqres.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiContractLogin {
    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}