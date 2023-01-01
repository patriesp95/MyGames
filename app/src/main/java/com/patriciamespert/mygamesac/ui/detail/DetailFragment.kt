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
import com.patriciamespert.mygamesac.data.datasource.GamesRepository
import com.patriciamespert.mygamesac.usecases.*
import com.patriciamespert.usecases.FindGameUseCase
import com.patriciamespert.usecases.RequestGameUseCase
import com.patriciamespert.usecases.SwitchGameFavoriteUseCase

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        val repository = GamesRepository(requireActivity().app)
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