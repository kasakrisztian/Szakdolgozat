package com.example.szakdolgozat.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.szakdolgozat.data.model.Game
import com.example.szakdolgozat.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val repository = GameRepository()
    private val gameId = savedStateHandle.get<Int>("id")

    private val _gameDetail = MutableStateFlow<Game?>(null)
    val gameDetail = _gameDetail.asStateFlow()

    init {
        getGameDetail()
    }

    private fun getGameDetail() {
        viewModelScope.launch {
            gameId?.let { id ->
                _gameDetail.value = repository.getGameDetail(id)
            }
        }
    }
}