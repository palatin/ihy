package com.palatin.ihy.data.ext

import android.database.Cursor

fun Cursor.getStringOrNull(columnIndex: Int): String? = kotlin.runCatching { getString(columnIndex) }.getOrNull()