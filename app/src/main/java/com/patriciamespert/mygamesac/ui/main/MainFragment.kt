package com.patriciamespert.mygamesac.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.FragmentMainBinding
import com.patriciamespert.mygamesac.model.GamesRepository
import com.patriciamespert.mygamesac.ui.detail.DetailFragment
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(GamesRepository(requireActivity().application)) }

    private val adapter = GamesAdapter { viewModel.onGameClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state){binding.updateUI(it)}

    }


    private fun FragmentMainBinding.updateUI(state: MainViewModel.UiState) {
        progress.visible = state.loading
        state.games?.let(adapter::submitList)
        state.navigateTo?.let(::navigateTo)
    }


    private fun navigateTo(game: GameDetailResponse){
        val navAction = MainFragmentDirections.actionMainToDetail(game)
        findNavController().navigate(navAction)
        viewModel.onNavigationDone()
    }

}