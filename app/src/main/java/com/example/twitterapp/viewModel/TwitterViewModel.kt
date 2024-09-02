package com.example.twitterapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterapp.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TwitterViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(TwitterUIState())
    val uiState: StateFlow<TwitterUIState> = _uiState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            try {
                _uiState.value = TwitterUIState(isLoading = true)
                val posts = fetchPosts()
                _uiState.value = TwitterUIState(posts = posts)
            } catch (e: Exception) {
                _uiState.value = TwitterUIState(error = "Error al cargar los posts")
            }
        }
    }

    private suspend fun fetchPosts(): List<Post> {
        return listOf(
            Post("Username #1"),
            Post("Username #2"),
            Post("Username #3")
        )
    }
}