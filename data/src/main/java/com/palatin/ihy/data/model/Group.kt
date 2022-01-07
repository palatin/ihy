package com.palatin.ihy.data.model


sealed interface GroupedSongs {

    val id: String
    val name: String
    val songs: List<Song>
    val image: String?
}

data class AlbumGroup(
    val album: Album,
    override val songs: List<Song>,
) : GroupedSongs {

    override val id: String by album::id

    override val name: String by album::name

    override val image: String? by album::image

}