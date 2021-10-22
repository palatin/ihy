package com.palatin.ihy.screen.library

import androidx.lifecycle.ViewModel
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song

class LibraryViewModel : ViewModel() {
}

data class LibraryViewState(
    val groups: List<GroupedSongs>,
    val songs: List<Song>
)