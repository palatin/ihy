package com.palatin.ihy.ui.screens.main

import android.graphics.BlendMode
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.palatin.ihy.ui.components.ContentCard
import com.palatin.ihy.ui.components.SearchView
import com.palatin.ihy.ui.theme.Typography

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .verticalScroll(state = rememberScrollState())
    ) {
        Text(
            text = "Library",
            style = Typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        SearchView(
            text = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        RecentlyPlayed(listOf(Song("", ""), Song("", ""), Song("", "")))
    }
}


@Composable
fun RecentlyPlayed(songs: List<Song>) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.7f),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(songs) { item ->
            ContentCard()
        }
    }
}

@Preview(name = "Library light preview", device = Devices.PIXEL)
@Composable
private fun LibraryPreview() {
    LibraryScreen(
    )
}