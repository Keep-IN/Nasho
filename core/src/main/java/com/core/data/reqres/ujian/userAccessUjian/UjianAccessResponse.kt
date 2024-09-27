package com.core.data.reqres.ujian.userAccessUjian

import com.google.gson.annotations.SerializedName

data class UjianAccessResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: Data
)