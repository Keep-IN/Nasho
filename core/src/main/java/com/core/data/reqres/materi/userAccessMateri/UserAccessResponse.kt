package com.core.data.reqres.materi.userAccessMateri


import com.google.gson.annotations.SerializedName

data class UserAccessResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)