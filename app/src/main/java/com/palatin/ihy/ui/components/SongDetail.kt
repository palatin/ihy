package com.palatin.ihy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import coil.ImageLoader
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Scale
import com.google.accompanist.coil.rememberCoilPainter
import com.palatin.ihy.R
import com.palatin.ihy.ui.screens.main.Song
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@Composable
fun SongDetail(song: Song) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailSongThumbnail(thumbnail = song.thumbnail)
        SongDescription(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
            ,
            title = "123",
            subtitle = "123"
        )
        Text(
            modifier = Modifier
                .padding(end = 16.dp),
            text = "2:37"
        )

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "")
        }
    }
}


@Composable
fun DetailSongThumbnail(
    modifier: Modifier = Modifier,
    thumbnail: String?
) {
    val color = MaterialTheme.colors.primary.copy(alpha = 0.2f)
    Image(
        painter = rememberCoilPainter(
            thumbnail ?: R.drawable.ic_baseline_search_24,
            previewPlaceholder = R.drawable.ic_baseline_search_24,
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f, true)
            .drawWithCache {
                onDrawBehind {
                    // drawing here rather than background to know the size of card
                    drawRoundRect(
                        color = color,
                        cornerRadius = CornerRadius(this.drawContext.size.height / 3f)
                    )
                }
            }
            .padding(10.dp)
    )

}

@Composable
fun SongDescription(modifier: Modifier = Modifier,
                    title: String, subtitle: String) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = title,
            style = MaterialTheme.typography.h6
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = subtitle,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
private fun SongDetailPreview() {
    Box(
        Modifier
            .fillMaxWidth()
            .requiredHeight(80.dp)) {
        SongDetail(song = Song("", null, ""))
    }
}