package com.core.data.reqres.quiz.quizDiscussion


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("benar")
    val benar: Boolean,
    @SerializedName("jawaban_details")
    val jawabanDetails: List<JawabanDetail>,
    @SerializedName("soal")
    val soal: String,
    @SerializedName("soal_quiz")
    val soalQuiz: String
)