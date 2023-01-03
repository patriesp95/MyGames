package com.patriciamespert.mygamesac.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.databinding.FragmentDetailBinding
import com.patriciamespert.mygamesac.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()


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