package com.palatin.ihy.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.palatin.ihy.theme.Grey

@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    title: String,
    backgroundUri: String? = null,
    shape: Shape = RoundedCornerShape(20.dp),
) {

    Card(
        modifier = modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = shape,
        backgroundColor = Grey,
        elevation = 8.dp
    ) {

        Image(
            painter = rememberImagePainter(
                data = backgroundUri
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        BoxWithConstraints {
            Box(modifier = Modifier
                .requiredHeight(this.maxHeight.times(0.17f))
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
            ) {
                Image(painter = rememberImagePainter(
                    data = backgroundUri,
                    builder = {
                        //todo make blurring only on partial region cutting image with [util.SubRegionTransformation]
                        transformations(BlurTransformation(LocalContext.current, radius = 10f, sampling = 10f))
                    }
                ), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize(), alignment = Alignment.BottomCenter)
                Text(
                    text = title,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

    }
}

@Preview
@Composable
private fun ContentCardPreview() {
    Box(
        Modifier
            .width(250.dp)
            .height(350.dp)) {
        ContentCard(title = "Test",
            backgroundUri = null
        )
    }
}