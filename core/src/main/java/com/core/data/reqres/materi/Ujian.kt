package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Ujian(
    @SerializedName("id")
    val id: String,
    @SerializedName("riwayat")
    val riwayat: Any
)