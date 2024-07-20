package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.quiz.QuizResponse
import com.core.data.reqres.quiz.quizAccessRequest.QuizAccessRequest
import com.core.data.reqres.quiz.quizDiscussion.QuizDiscussionResponse
import com.core.data.reqres.quiz.quizGrade.QuizGradeResponse
import com.core.data.reqres.quiz.userAccessQuiz.QuizAccesResponse
import com.core.di.ApiContractQuiz
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val api: ApiContractQuiz
) {
    fun accesQuiz(id: Int, quizAccessRequest: QuizAccessRequest): LiveData<Result<QuizAccesResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.accesQuiz(id, quizAccessRequest)
        val responseBody = response.body()
        try {
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

    fun getQuiz(id: Int): LiveData<Result<QuizResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getQuiz(id)
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

    fun getQuizGrade(id: Int): LiveData<Result<QuizGradeResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getQuizGrade(id)
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

    fun getQuizDiscussion(id: Int): LiveData<Result<QuizDiscussionResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getQuizDiscussion(id)
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