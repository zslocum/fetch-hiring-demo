package com.cryptictruth.fetchhiringdemo

import com.cryptictruth.fetchhiringdemo.models.HiringListItem
import com.cryptictruth.fetchhiringdemo.viewmodels.filterAndGroupListItems
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HiringDemoUnitTest {
    @Test
    fun filterAndSortFilters() {
        val originalList = listOf(
            HiringListItem(0,0,""),
            HiringListItem(1, 1, null)
        )
        val finalList = filterAndGroupListItems(originalList)
        assertEquals(finalList.count(), 0)
    }

    @Test
    fun filterAndSortGroupedProperly() {
        val originalList = listOf(
            HiringListItem(0,0,"Item 1"),
            HiringListItem(1, 0, "Item 2"),
            HiringListItem(2, 1, "Item 3"),
            HiringListItem(3, 1, "Item 4")
        )
        val finalList = filterAndGroupListItems(originalList)
        assertEquals(finalList.count(), 2)
        assertTrue(finalList.all { it.items.count() == 2 })
    }
}