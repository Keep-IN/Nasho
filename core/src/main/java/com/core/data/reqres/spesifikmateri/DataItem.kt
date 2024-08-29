package com.core.data.reqres.spesifikmateri

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("phase")
    val phase: Int,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("kategori")
    val kategori: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("id_quiz")
    val idQuiz: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("isi")
    val isi: String,

    @field:SerializedName("tingkat")
    val tingkat: Int,

    @field:SerializedName("linkvideo")
    val linkvideo: String
)