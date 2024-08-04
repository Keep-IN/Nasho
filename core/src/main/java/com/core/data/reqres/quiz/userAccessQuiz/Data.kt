package com.core.data.reqres.quiz.userAccessQuiz


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id_mengambil_quiz")
    val idMengambilQuiz: List<IdMengambilQuiz>
)