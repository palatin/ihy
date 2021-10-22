package com.palatin.ihy.data.datasource.song

import com.palatin.ihy.data.model.Song
import kotlinx.coroutines.flow.Flow

interface SongDataSource {

    fun getAllSongs(): Flow<List<Song>>

}