package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.quiz.QuizResponse
import com.core.data.reqres.quiz.answerValidationReqRes.QuizAnswerRequest
import com.core.data.reqres.quiz.answerValidationReqRes.QuizAnswerResponse
import com.core.data.reqres.quiz.quizDiscussion.QuizDiscussionResponse
import com.core.data.reqres.quiz.quizGrade.QuizGradeResponse
import com.core.data.reqres.quiz.userAccessQuiz.QuizAccesResponse
import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerRequest
import com.core.data.reqres.ujian.answerValidationReqRes.UjianAnswerResponse
import com.core.data.reqres.ujian.test.UjianResponse
import com.core.data.reqres.ujian.ujianDiscussion.UjianDiscussionResponse
import com.core.data.reqres.ujian.ujianGrade.UjianGradeResponse
import com.core.data.reqres.ujian.userAccessUjian.UjianAccessRequest
import com.core.data.reqres.ujian.userAccessUjian.UjianAccessResponse
import com.core.di.ApiContractUjian
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UjianRepository @Inject constructor(
    private val api: ApiContractUjian
){
    fun accessUjian(id: String, request: UjianAccessRequest): LiveData<Result<UjianAccessResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = api.accesUjian(id, request)
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(Result.Success(responseBody))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody).getString("message")
                } catch (e: JSONException) {
                    "Unknown Error Occurred"
                }
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }


    fun getUjian(id: String): LiveData<Result<UjianResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getUjian(id)
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try{
                    JSONObject(errorBody).getString("message")
                }catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    fun getUjianGrade(id: String): LiveData<Result<UjianGradeResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getUjianGrade(id)
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try{
                    JSONObject(errorBody).getString("message")
                }catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    fun getUjianDiscussion(id: String): LiveData<Result<UjianDiscussionResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getUjianDiscussion(id)
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try{
                    JSONObject(errorBody).getString("message")
                }catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }

    fun postUjianAnswer(id: String, body: UjianAnswerRequest): LiveData<Result<UjianAnswerResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.postJawabanUjian(id, body)
        val responseBody = response.body()
        try{
            if(response.isSuccessful && responseBody != null){
                emit(Result.Success(responseBody))
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = try{
                    JSONObject(errorBody).getString("message")
                }catch (e: JSONException){
                    "Unknown Error Occured"
                }
                emit(Result.Error(errorMessage))
            }
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "An error occurred"))
        }
    }
}