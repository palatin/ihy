package com.palatin.ihy.screen.library

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.palatin.ihy.components.ContentCard
import com.palatin.ihy.components.SearchView
import com.palatin.ihy.components.SongListItem
import com.palatin.ihy.data.datasource.song.local.MediaStoreSongDataSource
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.repository.song.RealSongRepository
import com.palatin.ihy.theme.IhyTheme
import com.palatin.ihy.theme.Typography
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    viewModel: LibraryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val state = viewModel.state
    LibraryContent(modifier = modifier, state = state)
}

@Composable
private fun LibraryContent(
    modifier: Modifier = Modifier,
    state: LibraryViewState
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
                paddingHorizontal = paddingHorizontal
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

        val itemSpace = 16.dp
        // todo calculate also inner padding that used to make shadow inside Content card
        val itemWidth = maxWidth.minus(contentPadding + itemSpace.div(2)).div(visibleItems)

        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = contentPadding),
            horizontalArrangement = Arrangement.spacedBy(itemSpace)
        ) {
            if (groups.isNotEmpty()) {
                items(groups) { item ->
                    ContentCard(
                        title = item.name,
                        backgroundUri = item.image,
                        modifier = Modifier
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

    val context = LocalContext.current
    val songs = listOf(Song(
        id = 0,
        title = "Song 1",
        coverUri = ""
    ), Song(
        id = 1,
        title = "Song 2",
        coverUri = null
    ))

    IhyTheme {
        LibraryContent(
            state = LibraryViewState(
                groups = listOf(
                    GroupedSongs.Album("Album 1", listOf(), songs.first().coverUri),
                    GroupedSongs.Album("Album 2", listOf(), ""),
                    GroupedSongs.Album("Album 3", listOf(), null)
                    ),
                songs = songs
            )
        )
    }

}