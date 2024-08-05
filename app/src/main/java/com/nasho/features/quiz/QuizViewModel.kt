package com.nasho.features.quiz

import androidx.lifecycle.ViewModel
import com.core.data.repositories.QuizRepository
import com.core.data.reqres.quiz.quizAccessRequest.QuizAccessRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
): ViewModel() {
    fun getQuiz(id: Int) = repository.getQuiz(id)
    fun getQuizGrade(id: Int) = repository.getQuizGrade(id)
    fun getQuizDiscussion(id: String) = repository.getQuizDiscussion(id)
    fun postAccessQuiz(id: Int, quizAccessRequest: QuizAccessRequest) = repository.accessQuiz(id, quizAccessRequest)
}