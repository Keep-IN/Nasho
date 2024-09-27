package com.core.data.reqres.materi


import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("lulus")
    val lulus: Boolean,
    @SerializedName("nilai")
    val nilai: Int
)