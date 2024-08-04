package com.core.data.reqres.materi.kategoriMateri


import com.google.gson.annotations.SerializedName

data class KategoriMateriResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)