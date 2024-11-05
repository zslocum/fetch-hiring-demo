package com.cryptictruth.fetchhiringdemo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cryptictruth.fetchhiringdemo.R
import com.cryptictruth.fetchhiringdemo.viewmodels.HiringViewModel

@Composable
fun HiringListScreen(viewModel: HiringViewModel, modifier: Modifier) {
    val hiringList by viewModel.hiringListGroups.observeAsState(emptyList())
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    LaunchedEffect(Unit) {
        viewModel.fetchHiringList()
    }
    LazyColumn (
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
            group ->
            var expanded by remember { mutableStateOf(false) }
            Column (
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(10.dp))
                    .heightIn(max = 400.dp)
                    .width(340.dp)
                    .padding(15.dp)
                    .clickable { expanded = !expanded },
                verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        "List Group: ${group.listId}",
                        color = Color.White,
                        fontWeight = FontWeight(800)
                    )
                    Icon(
                        if(expanded)
                            painterResource(
                                R.drawable.expand_circle_up)
                        else
                            painterResource(
                                R.drawable.expand_circle_down),
                        contentDescription = "expand-collapse-icon",
                        tint = Color.White
                    )
                }
                if (expanded) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(
                            15.dp,
                            Alignment.CenterVertically
                        )
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
        }
    }
}