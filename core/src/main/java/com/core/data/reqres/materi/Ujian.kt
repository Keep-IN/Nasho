package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Ujian(
    @SerializedName("id")
    val id: String,
    @SerializedName("nama_ujian")
    val nama: String,
    @SerializedName("phase_ujian")
    var phase_ujian: Int,
    @SerializedName("kategori_ujian")
    val kategori: String,
    @SerializedName("riwayat")
    val riwayat: List<Riwayat>,
    val locked: Boolean
)