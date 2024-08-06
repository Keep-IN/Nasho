package com.core.data.reqres.quiz.answerValidationReqRes


import com.google.gson.annotations.SerializedName

data class QuizAnswerResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String
)