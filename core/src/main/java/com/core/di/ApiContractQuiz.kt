package com.core.di

import com.core.data.reqres.quiz.QuizResponse
import com.core.data.reqres.quiz.quizAccessRequest.QuizAccessRequest
import com.core.data.reqres.quiz.quizDiscussion.QuizDiscussionResponse
import com.core.data.reqres.quiz.quizGrade.QuizGradeResponse
import com.core.data.reqres.quiz.userAccessQuiz.QuizAccesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContractQuiz {
    @POST("quiz/{id}")
    suspend fun accesQuiz(
        @Path("id") id: Int,
        @Body quizAccessRequest: QuizAccessRequest
    ): Response<QuizAccesResponse>

    @GET("quiz/{id}")
    suspend fun getQuiz(
        @Path("id") id: Int
    ): Response<QuizResponse>

    @GET("quiz/nilai/{id}")
    suspend fun getQuizGrade(
        @Path("id") id: Int
    ): Response<QuizGradeResponse>

    @GET("quiz/pembahasan/{id}")
    suspend fun getQuizDiscussion(
        @Path("id") id: Int
    ): Response<QuizDiscussionResponse>
}