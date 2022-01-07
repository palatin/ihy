package com.palatin.ihy.di

import com.palatin.ihy.data.datasource.album.AlbumDataSource
import com.palatin.ihy.data.datasource.album.local.SongsAlbumDataSource
import com.palatin.ihy.data.datasource.song.SongDataSource
import com.palatin.ihy.data.datasource.song.local.MediaStoreSongDataSource
import com.palatin.ihy.data.repository.album.AlbumRepository
import com.palatin.ihy.data.repository.album.AlbumRepositoryImpl
import com.palatin.ihy.data.repository.song.RealSongRepository
import com.palatin.ihy.data.repository.song.SongRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class LibraryModule {

    @Binds
    abstract fun songRepository(realSongRepository: RealSongRepository): SongRepository

    @Binds
    abstract fun localSongDataSource(songDataSource: MediaStoreSongDataSource): SongDataSource

    @Binds
    abstract fun albumRepository(albumRepository: AlbumRepositoryImpl): AlbumRepository

    @Binds
    abstract fun albumDataSource(albumDataSource: SongsAlbumDataSource): AlbumDataSource
}