package com.core.data.reqres.quiz


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("pilihan")
    val pilihan: List<Pilihan>,
    @SerializedName("soal")
    val soal: String,
    @SerializedName("soal_id")
    val soalId: String
)