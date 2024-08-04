package com.core.di

import com.core.data.reqres.materi.MateriResponse
import com.core.data.reqres.materi.kategoriMateri.KategoriMateriResponse
import com.core.data.reqres.materi.spesificMateri.SpesificMateriResponse
import com.core.data.reqres.materi.userAccessMateri.UserAccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiContractMateri {
    @GET("materi")
    suspend fun getMateri(): Response<MateriResponse>

    @GET("materi/{id}")
    suspend fun  getSpecificMateri(
        @Path("id") id: Int
    ): Response<SpesificMateriResponse>

    @GET("kategoriMateri")
    suspend fun getKategoriMateri(): Response<KategoriMateriResponse>

    @POST("selectMateri/{id}")
    suspend fun postAccess(
        @Path("id") id: Int
    ): Response<UserAccessResponse>
}