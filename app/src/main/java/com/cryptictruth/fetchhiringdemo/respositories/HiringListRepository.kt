package com.cryptictruth.fetchhiringdemo.respositories

import com.cryptictruth.fetchhiringdemo.models.HiringListItem

class HiringListRepository {
    private val hiringListService = RetrofitInstance.hiringListService
    suspend fun getHiringList(): List<HiringListItem> {
        return hiringListService.getHiringList()
    }
}