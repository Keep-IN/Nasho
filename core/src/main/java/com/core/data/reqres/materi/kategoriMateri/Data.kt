package com.core.data.reqres.materi.kategoriMateri


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("jenis")
    val jenis: String
)