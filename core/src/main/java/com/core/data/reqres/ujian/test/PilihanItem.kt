package com.core.data.reqres.ujian.test

import com.google.gson.annotations.SerializedName

data class PilihanItem(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("jawaban")
    val jawaban: String
)