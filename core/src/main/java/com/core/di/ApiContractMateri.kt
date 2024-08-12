package com.core.di

import com.core.data.reqres.home.statistik.StatistikHomeResponse
import com.core.data.reqres.materi.MateriResponse
import com.core.data.reqres.materi.kategoriMateri.KategoriMateriResponse
import com.core.data.reqres.materi.spesificMateri.SpesificMateriResponse
import com.core.data.reqres.materi.userAccessMateri.UserAccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiContractMateri {
    @GET("materis")
    suspend fun getMateri(
        @Query("kategori") kategori: String
    ): Response<MateriResponse>

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

    @GET("statistik/home")
    suspend fun getHomeStatistik(): Response<StatistikHomeResponse>
}