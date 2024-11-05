package com.cryptictruth.fetchhiringdemo.respositories

import com.cryptictruth.fetchhiringdemo.services.HiringListService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val hiringListService: HiringListService by lazy {
        retrofit.create(HiringListService::class.java)
    }
}