package com.patriciamespert.mygamesac.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.app
import com.patriciamespert.mygamesac.databinding.FragmentDetailBinding
import com.patriciamespert.mygamesac.launchAndCollect
import com.patriciamespert.mygamesac.model.GamesRepository
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(safeArgs.id, GamesRepository(requireActivity().app))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.gameDetailToolbar.setNavigationOnClickListener{findNavController().popBackStack()}

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.game != null) {
                binding.detailedGame = state.game
            }
        }

        viewModel.onUiReady()
    }
}