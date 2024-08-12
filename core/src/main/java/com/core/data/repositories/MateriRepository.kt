package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.home.statistik.StatistikHomeResponse
import com.core.data.reqres.materi.MateriResponse
import com.core.data.reqres.materi.kategoriMateri.KategoriMateriResponse
import com.core.data.reqres.materi.spesificMateri.SpesificMateriResponse
import com.core.data.reqres.materi.userAccessMateri.UserAccessResponse
import com.core.data.reqres.quiz.QuizResponse
import com.core.di.ApiContractMateri
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MateriRepository @Inject constructor(
    private val api: ApiContractMateri
)
{
    fun getMateri(kategori:String): LiveData<Result<MateriResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getMateri(kategori)
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

    fun getSpesificMateri(id: Int): LiveData<Result<SpesificMateriResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getSpecificMateri(id)
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

    fun getMateriKategori(): LiveData<Result<KategoriMateriResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getKategoriMateri()
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

    fun postAccesMateri(id: Int): LiveData<Result<UserAccessResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.postAccess(id)
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

    fun getHomeStatistik(): LiveData<Result<StatistikHomeResponse>> = liveData {
        emit(Result.Loading)
        val response =  api.getHomeStatistik()
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