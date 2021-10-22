package com.palatin.ihy.screen.library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song

class LibraryViewModel : ViewModel() {

    var state by mutableStateOf(LibraryViewState())
    private set

}

data class LibraryViewState(
    val groups: List<GroupedSongs> = listOf(),
    val songs: List<Song> = listOf()
)