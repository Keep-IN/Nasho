package com.core.data.reqres.quiz


import com.google.gson.annotations.SerializedName

data class Pilihan(
    @SerializedName("id")
    val id: String,
    @SerializedName("jawaban")
    val jawaban: String
)