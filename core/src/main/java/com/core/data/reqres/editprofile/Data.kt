package com.core.data.reqres.editprofile

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)