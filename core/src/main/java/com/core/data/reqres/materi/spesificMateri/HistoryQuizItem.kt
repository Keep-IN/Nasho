package com.example.nashodummy.test

import com.google.gson.annotations.SerializedName

data class HistoryQuizItem(

    @field:SerializedName("id_mengambil_quiz")
    val idMengambilQuiz: String,

    @field:SerializedName("nilai")
    val nilai: Int
)