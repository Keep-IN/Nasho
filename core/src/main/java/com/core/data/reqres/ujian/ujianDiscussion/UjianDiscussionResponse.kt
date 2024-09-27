package com.core.data.reqres.ujian.ujianDiscussion

import com.google.gson.annotations.SerializedName

data class UjianDiscussionResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: List<DataItem>
)