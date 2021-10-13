package com.palatin.ihy.ui.data

data class Song(
    val name: String,
    val artist: String? = null,
    val thumbnail: String? = null,
    val imageUri: String? = null,
    val durationMillis: Long = 0
)
