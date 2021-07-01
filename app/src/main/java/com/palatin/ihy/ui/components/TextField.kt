package com.palatin.ihy.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp

@Composable
fun SearchView(
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = Dp(16f)
) {
    DefaultTextField(
        text = text,
        modifier = modifier,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        cornerRadius = cornerRadius
    )
}

@Composable
fun DefaultTextField(
    text: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = Dp(16f),
    leadingIcon: @Composable () -> Unit = {}
) {

    TextField(
        value = text,
        onValueChange = { text -> },
        modifier = modifier,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    )
}

@Preview
@Composable
private fun PreviewSearchViewLight() {
    SearchView(text = "Light")
}