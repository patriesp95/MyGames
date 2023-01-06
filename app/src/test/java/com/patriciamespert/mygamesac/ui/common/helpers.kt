package com.patriciamespert.mygamesac.ui.common

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.ui.FakeLocalDataSource
import com.patriciamespert.mygamesac.ui.FakeLocalDetailDataSource
import com.patriciamespert.mygamesac.ui.FakeRemoteDataSource
import com.patriciamespert.mygamesac.ui.FakeRemoteDetailDataSource

fun buildRepositoryWith(
    localData:List<Game>,
    remoteData: List<Game>,
    localDetailData: List<GameDetail>,
    remoteDetailData: List<GameDetail>
): GamesRepository {
    val localDataSource = FakeLocalDataSource().apply { inMemoryGames.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { games = remoteData }
    val localDetailDataSource = FakeLocalDetailDataSource().apply { inMemoryDetailedGames.value = localDetailData }
    val remoteDetailDataSource = FakeRemoteDetailDataSource().apply { detailedGames = remoteDetailData }
    return GamesRepository(localDataSource, remoteDataSource,localDetailDataSource,remoteDetailDataSource)
}