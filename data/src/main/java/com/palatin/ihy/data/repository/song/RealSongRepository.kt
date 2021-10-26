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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RealSongRepository @Inject constructor(
    private val localMediaSongDataSource: SongDataSource
) : SongRepository {


    override fun getAllSongs(): Flow<List<Song>> {
        return localMediaSongDataSource.getAllSongs()
    }

}