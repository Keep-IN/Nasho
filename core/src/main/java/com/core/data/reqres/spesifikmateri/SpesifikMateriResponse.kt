package com.core.data.reqres.spesifikmateri

import com.google.gson.annotations.SerializedName

data class SpesifikMateriResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: List<DataItem>
)