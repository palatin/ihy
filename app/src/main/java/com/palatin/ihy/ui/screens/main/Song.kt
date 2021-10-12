package com.palatin.ihy.ui.screens.main

data class Song(
    val name: String,
    val author: String? = null,
    val thumbnail: String? = null,
    val imageUri: String? = null,
    val durationMillis: Long = 0
)
