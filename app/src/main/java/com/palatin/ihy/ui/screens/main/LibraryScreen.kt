package com.palatin.ihy.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.palatin.ihy.ui.components.ContentCard
import com.palatin.ihy.ui.components.SearchView
import com.palatin.ihy.ui.components.SongListItem
import com.palatin.ihy.ui.data.GroupedSongs
import com.palatin.ihy.ui.data.Song
import com.palatin.ihy.ui.theme.Typography

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    state: LibraryViewState,
) {
    val paddingHorizontal = remember {
        16.dp
    }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingHorizontal)
            )
            SearchView(
                text = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = paddingHorizontal)
            )
        }
        item {
            Text(
                text = "Albums",
                style = Typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingHorizontal)
            )

            GroupedSongs(
                modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
                groups = state.groups,
                contentPadding = paddingHorizontal)

        }

        items(state.songs) { song ->
            SongListItem(
                song = song,
                modifier = Modifier.padding(horizontal = paddingHorizontal)
            )
        }
    }
}

/**
 * Draws the grouped items of songs
 * @param visibleItems the count of cards which will be visible
 * @param contentPadding the padding before first and after list item
 */
@Composable
fun GroupedSongs(
    modifier: Modifier = Modifier,
    groups: List<GroupedSongs>,
    visibleItems: Float = 2.3f,
    contentPadding: Dp = 16.dp,
) {
    BoxWithConstraints(modifier = modifier
        .fillMaxWidth()
        .aspectRatio(1.7f)) {

        // todo calculate also inner padding that used to make shadow inside Content card
        val itemWidth = maxWidth.minus(contentPadding).div(visibleItems)

        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = contentPadding)
        ) {
            if (groups.isNotEmpty()) {
                items(groups) { item ->
                    ContentCard(
                        title = item.name, modifier = Modifier
                            .fillMaxHeight()
                            .requiredWidth(itemWidth)
                    )
                }
            } else {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        Text(
                            text = "No albums",
                            style = Typography.h4,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
        }
    }
}


@Preview(name = "Library light preview", device = Devices.PIXEL)
@Composable
private fun LibraryPreview() {
    LibraryScreen(
        state = LibraryViewState(
            groups = listOf(
                GroupedSongs.Album("Album 1", listOf(), null),
                GroupedSongs.Album("Album 1", listOf(), null),
                GroupedSongs.Album("Album 1", listOf(), null)
            ),
            songs = listOf()
        )
    )
}