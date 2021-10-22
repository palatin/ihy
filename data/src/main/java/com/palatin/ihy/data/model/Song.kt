package com.palatin.ihy.data.model

data class Song(
    val id: Long,
    val title: String,
    val coverUri: String? = null,
    val durationMillis: Long = 0,
    val albumId: Long? = null,
    val albumName: String? = null,
    val artistId: Long? = null,
    val artistName: String? = null,
)