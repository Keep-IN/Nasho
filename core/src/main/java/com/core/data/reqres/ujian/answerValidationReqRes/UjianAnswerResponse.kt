package com.core.data.reqres.ujian.answerValidationReqRes

import com.google.gson.annotations.SerializedName

data class UjianAnswerResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: Data
)