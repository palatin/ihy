package com.palatin.ihy.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.palatin.ihy.ui.components.ContentCard
import com.palatin.ihy.ui.components.SearchView
import com.palatin.ihy.ui.components.SongListItem
import com.palatin.ihy.ui.theme.Typography

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        item {
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
        }
        item {
            RecentlyPlayed(listOf(Song("", ""), Song("", ""), Song("", "")))
        }
        item {
            ContentSection()
        }
        items(listOf(Song("123", ""), Song("123", ""), Song("123", ""))) { song ->
            SongListItem(
                song = song
            )
        }
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

// song or playlist
@Composable
fun ContentSection() {
}

@Preview(name = "Library light preview", device = Devices.PIXEL)
@Composable
private fun LibraryPreview() {
    LibraryScreen(
    )
}