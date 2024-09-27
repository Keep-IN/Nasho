package com.core.data.reqres.quiz.answerValidationReqRes


import com.google.gson.annotations.SerializedName

data class QuizAnswerRequest(
    @SerializedName("id_jawaban")
    val idJawaban: String,
    @SerializedName("id_soal")
    val idSoal: String
)