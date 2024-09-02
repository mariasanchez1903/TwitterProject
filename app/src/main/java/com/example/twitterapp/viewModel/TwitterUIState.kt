package com.example.twitterapp.viewModel

import com.example.twitterapp.Post

data class TwitterUIState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
