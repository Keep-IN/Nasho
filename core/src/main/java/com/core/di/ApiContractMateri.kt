package com.core.di

import com.core.data.reqres.spesifikmateri.SpesifikMateriResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiContractMateri {
//    @POST("quiz/cek/{id}")
//    suspend fun cekMateri(
//        @Path("id") id: Int
//    ): List<Materi>


    @GET("materi/{id}")
    suspend fun spesifikMateri(
        @Path("id") id: Int
    ): SpesifikMateriResponse

}