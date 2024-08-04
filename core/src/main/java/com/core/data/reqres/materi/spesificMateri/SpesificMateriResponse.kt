package com.core.data.reqres.materi.spesificMateri


import com.google.gson.annotations.SerializedName

data class SpesificMateriResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)