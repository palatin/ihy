package com.palatin.ihy.ui.data


sealed class GroupedSongs {

    abstract val name: String
    abstract val songs: List<Song>
    abstract val image: String?

    data class Album(
        override val name: String,
        override val songs: List<Song>,
        override val image: String?
    ) : GroupedSongs()
}