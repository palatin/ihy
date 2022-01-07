package com.palatin.ihy.data.datasource.song

import com.palatin.ihy.data.model.Song
import kotlinx.coroutines.flow.Flow

interface SongDataSource {

    fun getAllSongs(): List<Song>

}