package com.patriciamespert.mygamesac.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.FragmentMainBinding
import com.patriciamespert.mygamesac.model.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(GamesRepository(requireActivity().app)) }

    private val adapter = GamesAdapter { viewModel.onGameClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state){
            binding.loading = it.loading
            binding.games = it.games
        }
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

    private fun <T, U> Flow<T>.diff(mapf: (T) -> U, body: (U) -> Unit) {
        viewLifecycleOwner.launchAndCollect(
            flow = map(mapf).distinctUntilChanged(),
            body = body
        )
    }

}