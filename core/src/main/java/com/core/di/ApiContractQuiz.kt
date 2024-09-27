package com.core.di

import com.core.data.reqres.quiz.QuizResponse
import com.core.data.reqres.quiz.answerValidationReqRes.QuizAnswerRequest
import com.core.data.reqres.quiz.answerValidationReqRes.QuizAnswerResponse
import com.core.data.reqres.quiz.quizAccessRequest.QuizAccessRequest
import com.core.data.reqres.quiz.quizDiscussion.QuizDiscussionResponse
import com.core.data.reqres.quiz.quizGrade.QuizGradeResponse
import com.core.data.reqres.quiz.userAccessQuiz.QuizAccesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiContractQuiz {
    @POST("quiz/{id}")
    suspend fun accesQuiz(
        @Path("id") id: String
    ): Response<QuizAccesResponse>

    @GET("quiz/{id}")
    suspend fun getQuiz(
        @Path("id") id: String
    ): Response<QuizResponse>

    @GET("quiz/nilai/{id}")
    suspend fun getQuizGrade(
        @Path("id") id: String
    ): Response<QuizGradeResponse>

    @GET("quiz/pembahasan/{id}")
    suspend fun getQuizDiscussion(
        @Path("id") id: String
    ): Response<QuizDiscussionResponse>

    @POST("quiz/cek/{id}")
    suspend fun postJawabanQuiz(
        @Path("id") id: String,
        @Body body: QuizAnswerRequest
    ): Response<QuizAnswerResponse>
}