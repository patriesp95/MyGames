package com.patriciamespert.mygamesac.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.FragmentMainBinding
import com.patriciamespert.mygamesac.data.GamesRepository


class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(GamesRepository(requireActivity().app)) }

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