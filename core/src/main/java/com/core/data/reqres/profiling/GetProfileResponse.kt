package com.core.data.reqres.profiling

import com.google.gson.annotations.SerializedName

data class GetProfileResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: List<DataItem>
)