package com.core.data.reqres.quiz.quizDiscussion


import com.google.gson.annotations.SerializedName

data class JawabanDetail(
    @SerializedName("jawaban_benar")
    val jawabanBenar: String,
    @SerializedName("jawaban_user")
    val jawabanUser: String,
    @SerializedName("jawaban_user_id")
    val jawabanUserId: String
)