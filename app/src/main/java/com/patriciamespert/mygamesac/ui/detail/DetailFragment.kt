package com.patriciamespert.mygamesac.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.app
import com.patriciamespert.mygamesac.databinding.FragmentDetailBinding
import com.patriciamespert.mygamesac.launchAndCollect
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.framework.datasource.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameDetailServerDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameRoomDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameServerDataSource
import com.patriciamespert.mygamesac.usecases.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {

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

        DetailViewModelFactory(
            safeArgs.id,
            RequestGameUseCase(repository),
            FindGameUseCase(repository),
            SwitchGameFavoriteUseCase(repository)
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.gameDetailToolbar.setNavigationOnClickListener{findNavController().popBackStack()}
        binding.gameDetailFavorite.setOnClickListener {viewModel.onFavoriteClicked()}

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.game != null) {
                binding.detailedGame = state.game
            }
        }
    }
}