package com.palatin.ihy.screen.library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palatin.ihy.data.model.Album
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.repository.album.AlbumRepository
import com.palatin.ihy.data.repository.song.SongRepository
import com.palatin.ihy.data.util.dataOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val songRepository: SongRepository,
    private val albumRepository: AlbumRepository
) : ViewModel() {

    var state by mutableStateOf(LibraryViewState())
    private set

    init {
        songRepository.songs.onEach {
            state = state.copy(songs = it.dataOrNull() ?: emptyList())
        }.launchIn(viewModelScope)

        albumRepository.albums.onEach {
            state = state.copy(groups = it.dataOrNull() ?: emptyList())
        }.launchIn(viewModelScope)
    }

    fun fetchData() {

    }
}

data class LibraryViewState(
    val groups: List<Album> = listOf(),
    val songs: List<Song> = listOf()
)