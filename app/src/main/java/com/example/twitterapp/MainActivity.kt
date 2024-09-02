package com.example.twitterapp
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
    private val postList = arrayListOf<Post>()
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
        rvAdapterPost = RVAdapterPosts(emptyList())
        rvPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapterPost
        }
    }
    private fun handleUIState(uiState: TwitterUIState) {
        if (uiState.isLoading) {
        } else if (uiState.error != null) {
        } else {
            rvAdapterPost.updatePosts(uiState.posts)
        }
    }
    private fun initViews() {
        rvPosts = findViewById(R.id.rvPosts)
        initRV()
        fillPostList()
        rvAdapterPost.notifyDataSetChanged()
    }

    private fun initRV() {
        rvAdapterPost = RVAdapterPosts(postList = postList)
        rvPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapterPost
        }
    }

    private fun fillPostList() {
        for (i in 0 until 10) {
            postList.add(Post("Username #$i"))
        }
    }
}
