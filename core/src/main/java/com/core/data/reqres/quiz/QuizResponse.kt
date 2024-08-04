package com.core.data.reqres.quiz


import com.google.gson.annotations.SerializedName

data class QuizResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)