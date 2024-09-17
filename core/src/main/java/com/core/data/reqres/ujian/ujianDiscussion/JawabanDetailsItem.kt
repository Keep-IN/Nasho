package com.core.data.reqres.ujian.ujianDiscussion

import com.google.gson.annotations.SerializedName

data class JawabanDetailsItem(

    @field:SerializedName("jawaban_user")
    val jawabanUser: String,

    @field:SerializedName("jawaban_user_id")
    val jawabanUserId: String,

    @field:SerializedName("jawaban_benar")
    val jawabanBenar: String
)