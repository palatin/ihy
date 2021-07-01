package com.palatin.ihy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.palatin.ihy.ui.theme.Grey

@Composable
fun ContentCard() {

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(0.69f)
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Grey,
        elevation = 8.dp
    ) {
        Box {
            Box(modifier = Modifier
                .requiredHeight(50.dp)
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.7f))
                .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "Test",
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
            .height(800.dp)) {
        ContentCard()
    }
}