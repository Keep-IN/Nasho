package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("materi")
    val materi: List<DataMateri>,
    val jenis: String,
    val deskripsi: String,
)