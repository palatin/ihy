package com.palatin.ihy.data.datasource.song.local

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.palatin.ihy.data.datasource.song.SongDataSource
import com.palatin.ihy.data.ext.getStringOrNull
import com.palatin.ihy.data.model.Album
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.util.getMediaStoreCoverUri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * Uses [android.provider.MediaStore] to fetch the list of songs
 */
class MediaStoreSongDataSource @Inject constructor(
    private val contentResolver: ContentResolver
) : SongDataSource {



    override fun getAllSongs(): List<Song> {
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Albums.ALBUM_ID,
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Artists._ID,
            MediaStore.Audio.Artists.ARTIST
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val query = contentResolver.query(
            collection,
            projection,
            selection,
            null,
            null
        )
        val songs = ArrayList<Song>()
        query?.use { cursor ->
            while (cursor.moveToNext()) {
                kotlin.runCatching {
                    val albumId = cursor.getLong(cursor.getColumnIndexOrThrow(projection[3]))
                        songs.add(
                            Song(
                                id = cursor.getLong(cursor.getColumnIndexOrThrow(projection[0])).toString(),
                                title = cursor.getString(cursor.getColumnIndexOrThrow(projection[1])),
                                coverUri = getMediaStoreCoverUri(albumId).toString(),
                                durationMillis = cursor.getLong(cursor.getColumnIndexOrThrow(projection[2])),
                                albumId = albumId.toString(),
                                albumName = cursor.getStringOrNull(cursor.getColumnIndex(projection[4])),
                                artistId = cursor.getLong(cursor.getColumnIndexOrThrow(projection[5])).toString(),
                                artistName = cursor.getStringOrNull(cursor.getColumnIndex(projection[6]))
                            )
                        )
                }
            }
        }


        return songs
    }

}