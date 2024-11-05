package com.cryptictruth.fetchhiringdemo.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cryptictruth.fetchhiringdemo.R
import com.cryptictruth.fetchhiringdemo.ui.theme.FetchHiringDemoTheme

@Composable
fun ExpandableCard(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .background(Color.DarkGray, RoundedCornerShape(10.dp))
            .heightIn(max = 400.dp)
            .clickable { expanded = !expanded }
            .fillMaxWidth(.85f)
            .padding(15.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterVertically)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                title,
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
        if(expanded)
            content()
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    FetchHiringDemoTheme {
        ExpandableCard("Demo Card") {
            Text("Example Text")
        }
    }
}