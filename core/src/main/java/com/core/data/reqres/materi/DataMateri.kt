package com.core.data.reqres.materi

import com.google.gson.annotations.SerializedName

data class DataMateri (
    @SerializedName("phase")
    val phase: Int,
    @SerializedName("materi")
    val materi: List<Materi>,
    @SerializedName("ujian")
    val ujian: List<Ujian>
)