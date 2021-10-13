package com.palatin.ihy.ui.screens.main

import androidx.lifecycle.ViewModel
import com.palatin.ihy.ui.data.GroupedSongs
import com.palatin.ihy.ui.data.Song

class LibraryViewModel : ViewModel() {
}

data class LibraryViewState(
    val groups: List<GroupedSongs>,
    val songs: List<Song>
)