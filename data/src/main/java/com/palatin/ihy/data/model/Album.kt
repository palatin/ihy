package com.palatin.ihy.data.model

data class Album(
    val id: String,
    val name: String,
    val artist: ArtistRelation?,
    val trackCount: Int?,
    val image: String?,
) {

    data class ArtistRelation(
        val id: String,
        val name: String
    )
}
