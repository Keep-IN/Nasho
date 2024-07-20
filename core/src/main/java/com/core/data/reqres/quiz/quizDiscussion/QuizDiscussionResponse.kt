package com.core.data.reqres.quiz.quizDiscussion


import com.google.gson.annotations.SerializedName

data class QuizDiscussionResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String
)