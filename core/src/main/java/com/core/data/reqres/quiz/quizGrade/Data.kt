package com.core.data.reqres.quiz.quizGrade


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("quiz")
    val quiz: List<Quiz>
)