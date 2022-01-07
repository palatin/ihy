package com.palatin.ihy.data.datasource.album

import com.palatin.ihy.data.model.Album

interface AlbumDataSource {

    fun getAllAlbums(): List<Album>

}