package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class MateriResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)