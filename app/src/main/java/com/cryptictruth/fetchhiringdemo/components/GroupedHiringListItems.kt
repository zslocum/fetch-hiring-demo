package com.cryptictruth.fetchhiringdemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptictruth.fetchhiringdemo.models.HiringListGroup
import com.cryptictruth.fetchhiringdemo.models.HiringListItem
import com.cryptictruth.fetchhiringdemo.ui.theme.FetchHiringDemoTheme

@Composable
fun GroupedHiringListItems(group: HiringListGroup) {
    ExpandableCard("List Group: ${group.listId}") {
        LazyVerticalGrid (
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Adaptive(340.dp),
            verticalArrangement = Arrangement.spacedBy(
                15.dp,
                Alignment.CenterVertically
            ),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(group.items) {
                Text(
                    "ID: ${it.id}, ListId: ${it.listId}, Name:  ${it.name}",
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(5.dp))
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupPreview() {
    FetchHiringDemoTheme {
        GroupedHiringListItems(HiringListGroup(listId = 0,
            listOf(
                HiringListItem(0, 0, "Item 123"),
                HiringListItem(1, 0, "Item 125")
            )
        ))
    }
}