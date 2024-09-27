package com.core.data.reqres.ujian.test

import com.google.gson.annotations.SerializedName

data class UjianResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: List<DataItem>
)