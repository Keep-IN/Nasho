package com.core.data.reqres.ujian.ujianGrade

import com.google.gson.annotations.SerializedName

data class UjianItem(

    @field:SerializedName("nilai")
    val nilai: Int,

    @field:SerializedName("jumlah_salah")
    val jumlahSalah: Int,

    @field:SerializedName("lulus")
    val lulus: Boolean,

    @field:SerializedName("jumlah_benar")
    val jumlahBenar: Int,

    @field:SerializedName("jumlah_soal")
    val jumlahSoal: Int,

    @field:SerializedName("nama_ujian")
    val namaUjian: String
)