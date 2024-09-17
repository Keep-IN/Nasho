package com.core.data.reqres.ujian.ujianGrade

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("ujian")
    val ujian: List<UjianItem>
)