package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("materi")
    val materi: List<Materi>,
    @SerializedName("phase")
    val phase: Int,
    @SerializedName("ujian")
    val ujian: List<Ujian>
)