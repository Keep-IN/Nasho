package com.core.data.reqres.ujian.userAccessUjian

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("id_mengambil_ujian")
    val idMengambilUjian: List<IdMengambilUjianItem>
)