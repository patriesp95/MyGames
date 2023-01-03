package com.patriciamespert.mygamesac.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { vmFactory }

    private lateinit var mainState: MainState

    private val adapter = GamesAdapter { mainState.onGameClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.component.inject(this)
    }

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