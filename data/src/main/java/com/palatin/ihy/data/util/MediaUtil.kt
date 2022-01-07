package com.palatin.ihy.data.util

import android.content.ContentUris
import android.net.Uri

fun getMediaStoreCoverUri(albumId: Long): Uri {
    return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)
}