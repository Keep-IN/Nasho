package com.core.data.reqres.ujian.answerValidationReqRes

import com.google.gson.annotations.SerializedName

data class UjianAnswerRequest(

    @field:SerializedName("id_jawaban")
    val idJawaban: String,

    @field:SerializedName("id_soal")
    val idSoal: String
)