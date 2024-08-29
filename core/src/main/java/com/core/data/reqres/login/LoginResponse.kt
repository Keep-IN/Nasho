package com.core.data.reqres.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: DataLoginResponse,

    @field:SerializedName("msg")
    val msg: String

)