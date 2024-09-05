package com.core.data.reqres.materi.spesificMateri


import com.example.nashodummy.test.HistoryQuizItem
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_quiz")
    val idQuiz: String,
    @SerializedName("history_quiz")
    val historyQuiz: List<HistoryQuizItem>,
    @SerializedName("isi")
    val isi: String,
    @SerializedName("judul")
    val judul: String,
    @SerializedName("subjudul")
    val subjudul: String,
    @SerializedName("kategori")
    val kategori: String,
    @SerializedName("linkvideo")
    val linkvideo: String,
    @SerializedName("phase")
    val phase: Int,
    @SerializedName("tingkat")
    val tingkat: Int
)