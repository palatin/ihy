package com.palatin.ihy.data.repository.song

import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    val songs: Flow<Resource<List<Song>>>

    suspend fun fetchSongs()

    suspend fun getSongsByAlbum(albumId: String): Flow<Resource<List<Song>>>

    suspend fun addToFavorites(songId: String)

    suspend fun removeFromFavorites(songId: String)

}