package com.palatin.ihy.data.model

data class Song(
    val id: String,
    val title: String,
    val coverUri: String? = null,
    val durationMillis: Long = 0,
    val albumId: String? = null,
    val albumName: String? = null,
    val artistId: String? = null,
    val artistName: String? = null,
)