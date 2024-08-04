package com.core.data.reqres.quiz.quizGrade


import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("lulus")
    val lulus: Boolean,
    @SerializedName("nilai")
    val nilai: Int,
    @SerializedName("nama_quiz")
    val nama: String,
    @SerializedName("jumlah_soal")
    val jumlahSoal: Int,
    @SerializedName("jumlah_benar")
    val jumlahBenar: Int,
    @SerializedName("jumlah_salah")
    val jumlahSalah: Int

)