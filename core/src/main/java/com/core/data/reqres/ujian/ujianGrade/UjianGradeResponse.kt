package com.core.data.reqres.ujian.ujianGrade

import com.google.gson.annotations.SerializedName

data class UjianGradeResponse(

    @field:SerializedName("msg")
    val msg: String,

    @field:SerializedName("data")
    val data: Data
)