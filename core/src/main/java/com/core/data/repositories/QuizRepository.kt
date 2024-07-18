package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.quiz.QuizResponse
import com.core.di.ApiContractQuiz
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val api: ApiContractQuiz
) {
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
}