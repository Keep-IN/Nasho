package com.nasho.features.ujian

import androidx.lifecycle.ViewModel
import com.core.data.repositories.UjianRepository
import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerRequest
import com.core.data.reqres.ujian.userAccessUjian.UjianAccessRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UjianViewModel @Inject constructor(
    private val repository: UjianRepository
): ViewModel() {
    fun getUjian(id: String) = repository.getUjian(id)
    fun getUjianGrade(id: String) = repository.getUjianGrade(id)
    fun getUjianDiscussion(id: String) = repository.getUjianDiscussion(id)
    fun postAccessUjian(id: String, request: UjianAccessRequest) = repository.accessUjian(id, request)
    fun postUjianAnswer(id: String, body: UjianAnswerRequest) = repository.postUjianAnswer(id, body)
}