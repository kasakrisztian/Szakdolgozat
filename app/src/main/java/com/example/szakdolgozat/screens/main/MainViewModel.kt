package com.example.szakdolgozat.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.szakdolgozat.data.model.Game
import com.example.szakdolgozat.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = GameRepository()

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games = _games.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            _games.value = repository.getGames()
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.update { query }
    }

    fun search() {
        _games.value = repository.search(searchQuery.value)
    }
}