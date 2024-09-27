package com.core.data.reqres.editprofile

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: Data
)