package com.cryptictruth.fetchhiringdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptictruth.fetchhiringdemo.models.HiringListGroup
import com.cryptictruth.fetchhiringdemo.respositories.HiringListRepository
import kotlinx.coroutines.launch

class HiringViewModel : ViewModel() {
    private val repository = HiringListRepository()
    val hiringListGroups = MutableLiveData<List<HiringListGroup>>()
    val errorMessage = MutableLiveData<String?>(null)

    fun fetchHiringList() {
        viewModelScope.launch {
            try {
                val hiringListItems = repository.getHiringList()
                hiringListGroups.value = hiringListItems
                    .filter {
                        !it.name.isNullOrEmpty()
                    }
                    .groupBy { it.listId }
                    .map { HiringListGroup(it.key, it.value.sortedBy {
                        item -> item.name!!.split(" ")[1].toIntOrNull()
                    }) }
                    .sortedBy { it.listId }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
            }
        }
    }
}