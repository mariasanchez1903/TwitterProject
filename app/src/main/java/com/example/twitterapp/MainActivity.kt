package com.example.twitterapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitterapp.rv_activity.adapters.RVAdapterPosts
import com.example.twitterapp.viewModel.TwitterUIState
import com.example.twitterapp.viewModel.TwitterViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapterPost: RVAdapterPosts
    private val twitterViewModel: TwitterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initRecyclerView()

        lifecycleScope.launch {
            twitterViewModel.uiState.collect { uiState ->
                handleUIState(uiState)
            }
        }
    }

    private fun initRecyclerView() {
        rvPosts = findViewById(R.id.rvPosts)
        rvAdapterPost = RVAdapterPosts(emptyList()) { post ->
            val intent = Intent(this, PostDetailActivity::class.java).apply {
                putExtra("EXTRA_POST", post)
            }
            startActivity(intent)
        }
        rvPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapterPost
        }
    }

    private fun handleUIState(uiState: TwitterUIState) {
        if (uiState.isLoading) {
            // Manejo del estado de carga
        } else if (uiState.error != null) {
            // Manejo del error
        } else {
            rvAdapterPost.updatePosts(uiState.posts)
        }
    }

    private fun initViews() {
        rvPosts = findViewById(R.id.rvPosts)
    }
}

