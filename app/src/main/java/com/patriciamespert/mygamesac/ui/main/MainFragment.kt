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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(GamesRepository(requireActivity().application)) }

    private val adapter = GamesAdapter { viewModel.onGameClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        /*viewLifecycleOwner.launchAndCollect(
            viewModel.state.map { it.loading }.distinctUntilChanged()
        ){ visible ->
            binding.progress.visible = visible
        }

        viewLifecycleOwner.launchAndCollect(
            viewModel.state.map { it.games }.distinctUntilChanged()
        ){ games ->
            adapter.submitList(games)
        }

        viewLifecycleOwner.launchAndCollect(
            viewModel.state.map { it.navigateTo }.distinctUntilChanged()
        ){ game ->
            game?.let(::navigateTo)
        }*/

        with(viewModel.state) {
            diff({ it.games }) { it?.let(adapter::submitList) }
            diff({ it.loading }) { binding.progress.visible = it }
            diff({ it.navigateTo }) { it?.let(::navigateTo) }
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