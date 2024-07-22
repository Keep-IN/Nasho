package com.core.data.reqres.quiz.quizAccessRequest

import com.google.gson.annotations.SerializedName

data class QuizAccessRequest(
    @SerializedName("id_materi")
    val idMateri: Int,
    @SerializedName("uuid")
    val uuid: Int
)
