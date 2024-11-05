package com.cryptictruth.fetchhiringdemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cryptictruth.fetchhiringdemo.components.GroupedHiringListItems
import com.cryptictruth.fetchhiringdemo.viewmodels.HiringViewModel

@Composable
fun HiringListScreen(viewModel: HiringViewModel, modifier: Modifier) {
    val hiringList by viewModel.hiringListGroups.observeAsState(emptyList())
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    LaunchedEffect(Unit) {
        viewModel.fetchHiringList()
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (errorMessage != null) {
            item {
                Text(
                    errorMessage!!,
                    modifier = Modifier
                        .background(Color.Red, RoundedCornerShape(10.dp))
                        .padding(10.dp),
                    color = Color.White
                )
            }
        }
        items(hiringList) {
            GroupedHiringListItems(it)
        }
    }
}