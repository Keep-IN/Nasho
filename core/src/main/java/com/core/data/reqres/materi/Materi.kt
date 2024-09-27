package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Materi(
    @SerializedName("id")
    val id: String,
    @SerializedName("judul")
    val judul: String,
    @SerializedName("quiz")
    val quiz: List<Quiz>,
    @SerializedName("sudah_mengambil")
    val sudahMengambil: Boolean,
    @SerializedName("tingkat")
    val tingkat: Int,
    @SerializedName("phase")
    var phase: Int,
    val locked: Boolean
)