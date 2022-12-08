package com.patriciamespert.mygamesac.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.FragmentMainBinding
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.framework.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.framework.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.framework.server.detail.GameDetailServerDataSource
import com.patriciamespert.mygamesac.framework.server.main.GameServerDataSource
import com.patriciamespert.mygamesac.usecases.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.usecases.RequestPopularGamesUseCase


class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels {
        val localDataSource = GameRoomDataSource(requireActivity().app.db.gameDao())
        val remoteDataSource = GameServerDataSource(requireActivity().app.getString(R.string.api_key))
        val localGameDetailDataSource = GameDetailRoomDataSource(requireActivity().app.db.gameDetailDao())
        val remoteGameDetailDataSource = GameDetailServerDataSource(requireActivity().app.getString(R.string.api_key))
        val repository = GamesRepository(
            localDataSource,
            remoteDataSource,
            localGameDetailDataSource,
            remoteGameDetailDataSource
        )
        MainViewModelFactory(
            GetPopularGamesUseCase(repository),
            RequestPopularGamesUseCase(repository)
        )
    }

    private lateinit var mainState: MainState

    private val adapter = GamesAdapter { mainState.onGameClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state){
            binding.loading = it.loading
            binding.games = it.games
        }

    }

}