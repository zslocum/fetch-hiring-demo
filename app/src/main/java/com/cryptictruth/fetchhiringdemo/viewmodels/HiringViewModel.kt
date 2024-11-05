package com.cryptictruth.fetchhiringdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptictruth.fetchhiringdemo.models.HiringListGroup
import com.cryptictruth.fetchhiringdemo.models.HiringListItem
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
                hiringListGroups.value = filterAndGroupListItems(hiringListItems)
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage
            }
        }
    }
}

fun filterAndGroupListItems(hiringList: List<HiringListItem>) : List<HiringListGroup> {
    return hiringList
        .filter {
            !it.name.isNullOrEmpty()
        }
        .groupBy { it.listId }
        .map { HiringListGroup(it.key, it.value.sortedWith(
            compareBy(primarySortFunction).thenBy(secondarySortFunction)
        )
        ) }
        .sortedBy { it.listId }
}

val secondarySortFunction = {
    listItem: HiringListItem ->
    val splitName = listItem.name?.split(" ") ?: listOf("")
    if(splitName.count() > 1 && splitName[1].toIntOrNull() != null)
        splitName[1].toInt()
    else
        0
}

val primarySortFunction = {
    listItem: HiringListItem ->
    val splitName = listItem.name?.split(" ") ?: listOf("")
    if(splitName.count() < 2 || splitName[1].toIntOrNull() == null)
        listItem.name ?: ""
    else
        splitName[0]
}
