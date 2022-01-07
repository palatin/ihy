package com.palatin.ihy.data.datasource.album.local

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.palatin.ihy.data.datasource.album.AlbumDataSource
import com.palatin.ihy.data.datasource.song.SongDataSource
import com.palatin.ihy.data.ext.getStringOrNull
import com.palatin.ihy.data.model.Album
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.util.getMediaStoreCoverUri
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 */
class SongsAlbumDataSource @Inject constructor(
    private val songDataSource: SongDataSource
) : AlbumDataSource {


    override fun getAllAlbums(): List<Album> {
        val songs = songDataSource.getAllSongs()
        val albums = arrayListOf<Album>()
        val songsByAlbums = songs.filter { it.albumId != null }.groupBy {
            it.albumId!!
        }

        songsByAlbums.forEach {
            it.value.firstOrNull()?.let { song ->
                albums.add(
                    Album(
                        id = it.key,
                        name = song.albumName ?: "Unknown name",
                        artist = null,
                        trackCount = it.value.size,
                        image = getMediaStoreCoverUri(
                            it.key.toLongOrNull() ?: return@let
                        ).toString()
                    )
                )
            }
        }

        return albums
    }


}