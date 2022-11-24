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
import com.patriciamespert.mygamesac.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireNotNull(safeArgs.game))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.gameDetailToolbar.setNavigationOnClickListener{findNavController().popBackStack()}

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    binding.updateUI(it)
                }
            }
        }
    }

    private fun FragmentDetailBinding.updateUI(state: DetailViewModel.UiState ){
        val game = state.game
        //requireActivity().title = game.gameName
        Glide.with(this@DetailFragment)
            .load(game.gameBackgroundImage)
            .into(backdrop)
        description.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(game.gameDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(game.gameDescription)
        }
        viewModel.bindDetailInfo(detailGameInfo, game)
    }
}