package com.palatin.ihy.screen.library

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palatin.ihy.data.model.GroupedSongs
import com.palatin.ihy.data.model.Song
import com.palatin.ihy.data.repository.song.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    var state by mutableStateOf(LibraryViewState())
    private set

    init {
        songRepository.getAllSongs().onEach {
            state = state.copy(songs = it)
        }.launchIn(viewModelScope)
    }

    fun fetchData() {

    }
}

data class LibraryViewState(
    val groups: List<GroupedSongs> = listOf(),
    val songs: List<Song> = listOf()
)