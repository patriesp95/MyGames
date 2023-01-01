package com.patriciamespert.mygamesac.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.patriciamespert.domain.Game
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildMainState(
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController()
) = MainState(scope, navController)

class MainState(
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onGameClicked(game: Game) {
        val action = MainFragmentDirections.actionMainToDetail(game.id)
        navController.navigate(action)
    }

}