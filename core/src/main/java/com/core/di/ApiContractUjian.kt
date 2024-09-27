package com.core.di

import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerRequest
import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerResponse
import com.core.data.reqres.ujian.test.UjianResponse
import com.core.data.reqres.ujian.ujianDiscussion.UjianDiscussionResponse
import com.core.data.reqres.ujian.ujianGrade.UjianGradeResponse
import com.core.data.reqres.ujian.userAccessUjian.UjianAccessRequest
import com.core.data.reqres.ujian.userAccessUjian.UjianAccessResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContractUjian {

    @POST("ujian/{id}")
    suspend fun accesUjian(
        @Path("id") id: String,
        @Body request: UjianAccessRequest
    ): Response<UjianAccessResponse>

    @GET("ujian/{id}")
    suspend fun getUjian(
        @Path("id") id: String
    ): Response<UjianResponse>

    @GET("ujian/nilai/{id}")
    suspend fun getUjianGrade(
        @Path("id") id: String
    ): Response<UjianGradeResponse>

    @GET("ujian/pembahasan/{id}")
    suspend fun getUjianDiscussion(
        @Path("id") id: String
    ): Response<UjianDiscussionResponse>

    @POST("ujian/cek/{id}")
    suspend fun postJawabanUjian(
        @Path("id") id: String,
        @Body body: UjianAnswerRequest
    ): Response<UjianAnswerResponse>


}