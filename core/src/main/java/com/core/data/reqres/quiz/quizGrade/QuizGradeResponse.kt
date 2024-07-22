package com.core.data.reqres.quiz.quizGrade


import com.google.gson.annotations.SerializedName

data class QuizGradeResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
)