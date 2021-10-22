package com.palatin.ihy.components

import android.graphics.ColorSpace
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentCard(
    modifier: Modifier = Modifier,
    title: String,
    backgroundUri: String? = null,
    shape: Shape = RoundedCornerShape(20.dp),
) {

    Card(
        onClick = {},
        modifier = modifier
            .padding(bottom = 8.dp),
        shape = shape,
        backgroundColor = Grey,
        elevation = 16.dp
    ) {

        Image(
            painter = rememberImagePainter(
                data = backgroundUri
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            contentScale = ContentScale.Crop
        )
        BoxWithConstraints {

            val overlayColor = MaterialTheme.colors.surface.copy(alpha = 0.15f)

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
                ), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.BottomCenter
                )
                // overlay if covered image is light
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(overlayColor))
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