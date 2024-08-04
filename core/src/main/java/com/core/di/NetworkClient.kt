package com.core.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.core.data.repositories.MateriRepository
import com.core.data.repositories.QuizRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkClient {
    companion object{
        private const val  BASE_URL ="https://enormous-mint-tomcat.ngrok-free.app/"
        private const val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Ijk2NTNjY2FhLTViYjktNGQ4Yy1iYTUyLTYyNDdhZWYxYjU4MyIsInVzZXJuYW1lIjoiaGFyZHlmZWJyeWFuIiwiZW1haWwiOiJoYXJkeUBnbWFpbC5jb20iLCJyb2xlIjoiYWM4N2ZkMjEtMmY5NC00YzFkLWE1ODItZmZlNjkxZmVmNDUwIiwiaWF0IjoxNzIyMTcwNzYxfQ.268IwJWJI4VByVbXCSDhuD1IM6pLQoxLK2LDezM_c54"
    }
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                val response = chain.proceed(requestBuilder)
                response
            }
            .build()
    }

    @Singleton
    @Provides
    fun getAuthToken(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString("token", "") ?: ""
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideApiMateri(retrofit: Retrofit): ApiContractMateri =
        retrofit.create(ApiContractMateri::class.java)

    @Singleton
    @Provides
    fun provideApiQuiz(retrofit: Retrofit): ApiContractQuiz =
        retrofit.create(ApiContractQuiz::class.java)

    @Singleton
    @Provides
    fun provideApiSignup(retrofit: Retrofit): ApiContractSignup =
        retrofit.create(ApiContractSignup::class.java)
}