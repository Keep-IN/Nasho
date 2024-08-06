package com.core.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.data.network.Result
import com.core.data.reqres.spesifikmateri.SpesifikMateriResponse
import com.core.di.ApiContractMateri
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MateriRepositry @Inject constructor(
private val api: ApiContractMateri
){
    fun spekmateri(
        id: Int
    ): LiveData<Result<SpesifikMateriResponse>> = liveData {
        emit(Result.Loading)
        val response = api.spesifikMateri(id)

        try{
            if (response.data.isNotEmpty()){
                emit(Result.Success(response))
            } else{
                emit(Result.Error("Data Kosong"))
            }
            } catch (e: Exception){
            e.message?.let { Result.Error(it) }?.let { emit(it) }

        }
    }
}