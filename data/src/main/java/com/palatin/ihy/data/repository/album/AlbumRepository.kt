package com.palatin.ihy.data.repository.album

import com.palatin.ihy.data.model.Album
import com.palatin.ihy.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    val albums: Flow<Resource<List<Album>>>

    suspend fun fetchAlbums()

}