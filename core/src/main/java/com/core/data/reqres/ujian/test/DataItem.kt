package com.core.data.reqres.ujian.test

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("nama_ujian")
    val namaUjian: String,

    @field:SerializedName("pembahasan")
    val pembahasan: String,

    @field:SerializedName("soal")
    val soal: String,

    @field:SerializedName("pilihan")
    val pilihan: List<PilihanItem>,

    @field:SerializedName("soal_id")
    val soalId: String,

    @field:SerializedName("jawaban_benar")
    val jawabanBenar: String
)