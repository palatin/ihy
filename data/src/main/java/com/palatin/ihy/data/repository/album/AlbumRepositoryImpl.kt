package com.palatin.ihy.data.repository.album

import com.palatin.ihy.data.datasource.album.AlbumDataSource
import com.palatin.ihy.data.model.Album
import com.palatin.ihy.data.util.Resource
import com.palatin.ihy.data.util.toResource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onSubscription
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val albumDataSource: AlbumDataSource) : AlbumRepository {

    private val albumsSharedFlow = MutableSharedFlow<Resource<List<Album>>>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val albums: Flow<Resource<List<Album>>>
        get() = albumsSharedFlow.onSubscription {
            if (albumsSharedFlow.replayCache.isEmpty())
                fetchAlbums()
        }

    override suspend fun fetchAlbums() {
        albumsSharedFlow.emit(Resource.Loading)

        albumsSharedFlow.emit(
            kotlin.runCatching {
                albumDataSource.getAllAlbums()
            }.toResource()
        )
    }

}