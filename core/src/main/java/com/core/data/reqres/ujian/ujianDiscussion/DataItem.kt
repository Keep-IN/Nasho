package com.core.data.reqres.ujian.ujianDiscussion

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("pembahasan")
    val pembahasan: String,

    @field:SerializedName("soal_ujian")
    val soalUjian: String,

    @field:SerializedName("soal")
    val soal: String,

    @field:SerializedName("benar")
    val benar: Boolean,

    @field:SerializedName("jawaban_details")
    val jawabanDetails: List<JawabanDetailsItem>
)