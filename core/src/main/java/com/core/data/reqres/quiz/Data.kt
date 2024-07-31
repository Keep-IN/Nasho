package com.core.data.reqres.quiz


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("nama_quiz")
    val nama: String,
    @SerializedName("pilihan")
    val pilihan: List<Pilihan>,
    @SerializedName("soal")
    val soal: String,
    @SerializedName("soal_id")
    val soalId: String
)