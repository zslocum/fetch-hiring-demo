package com.cryptictruth.fetchhiringdemo.services

import com.cryptictruth.fetchhiringdemo.models.HiringListItem
import retrofit2.http.GET

interface HiringListService {
    @GET("hiring.json")
    suspend fun getHiringList(): List<HiringListItem>
}