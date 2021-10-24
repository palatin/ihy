package com.palatin.ihy.data.repository.song

import com.palatin.ihy.data.model.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {

    fun getAllSongs(): Flow<List<Song>>

}