package com.core.data.reqres.login

import com.google.gson.annotations.SerializedName

data class DataLoginResponse(

    @field:SerializedName("accessToken")
    val accessToken: String
)