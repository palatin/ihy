package com.palatin.ihy.data.repository.song

import android.content.ContentResolver
import android.content.ContentUris
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.palatin.ihy.data.datasource.song.SongDataSource
import com.palatin.ihy.data.ext.getStringOrNull
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.util.Resource
import com.palatin.ihy.data.util.isSuccess
import com.palatin.ihy.data.util.toResource
import com.palatin.ihy.data.util.transform
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RealSongRepository @Inject constructor(
    private val localMediaSongDataSource: SongDataSource
) : SongRepository {

    private val songsMutableFlow = MutableSharedFlow<Resource<List<Song>>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override val songs: Flow<Resource<List<Song>>>
        get() = songsMutableFlow.onSubscription {
            if (songsMutableFlow.replayCache.isEmpty())
                fetchSongs()
        }

    override suspend fun getSongsByAlbum(albumId: String): Flow<Resource<List<Song>>> {
        return songsMutableFlow.map { it.transform { it.filter { it.albumId == albumId } }}
    }

    override suspend fun fetchSongs() {

        songsMutableFlow.emit(Resource.Loading)

        songsMutableFlow.emit(
            kotlin.runCatching {
                localMediaSongDataSource.getAllSongs()
            }.toResource()
        )
    }

    override suspend fun addToFavorites(songId: String) {

    }

    override suspend fun removeFromFavorites(songId: String) {
    }



}