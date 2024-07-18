package com.core.di

import com.core.data.reqres.quiz.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiContractQuiz {
    @GET("quiz/{id}")
    suspend fun getQuiz(
        @Path("id") id: Int
    ): Response<QuizResponse>

}