package com.core.data.reqres.editpassword

import com.google.gson.annotations.SerializedName

data class EditPasswordRequest(

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("retyped-password")
    val retypedPassword: String,

    @field:SerializedName("OldPassword")
    val oldPassword: String
)