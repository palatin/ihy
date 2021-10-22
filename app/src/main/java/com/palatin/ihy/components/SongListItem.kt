package com.palatin.ihy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.palatin.ihy.R
import com.palatin.ihy.data.model.Song

@Composable
fun SongListItem(modifier: Modifier = Modifier, paddingHorizontal: Dp = 16.dp, song: Song) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            onClick = {}
        )
        .padding(vertical = 8.dp, horizontal = paddingHorizontal),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SongItemThumbnail(thumbnail = song.coverUri)
        SongDescription(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
            ,
            title = song.title.takeIf { it.isNotBlank() } ?: "Unnamed",
            subtitle = song.artistName ?: "Unknown artist"
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
fun SongItemThumbnail(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    thumbnail: String?
) {
    val color = MaterialTheme.colors.primary.copy(alpha = 0.2f)
    val painter = rememberImagePainter(
        thumbnail ?: "",
        builder = {
            placeholder(R.drawable.ic_baseline_search_24)
            error(R.drawable.ic_baseline_search_24)
        }
    )


    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f, true)
            .clip(shape)
            .background(color)
            .padding(if (painter.state !is ImagePainter.State.Success) 8.dp else 0.dp), // whenever we have placeholder - add padding to see the shape
        contentScale = ContentScale.Crop
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
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = subtitle,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun SongDetailPreview() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.White)
    ) {
        SongListItem(song = Song(0,"",
            coverUri = null,
            durationMillis = 2000,
            artistId = 0
        ))
    }
}