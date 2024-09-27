package com.core.data.reqres.quiz.userAccessQuiz


import com.google.gson.annotations.SerializedName

data class QuizAccesResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
)