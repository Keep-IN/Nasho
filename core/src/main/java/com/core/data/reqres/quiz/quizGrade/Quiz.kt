package com.core.data.reqres.quiz.quizGrade


import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("lulus")
    val lulus: Boolean,
    @SerializedName("nilai")
    val nilai: Int
)