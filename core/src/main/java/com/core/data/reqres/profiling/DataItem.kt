package com.core.data.reqres.profiling

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)