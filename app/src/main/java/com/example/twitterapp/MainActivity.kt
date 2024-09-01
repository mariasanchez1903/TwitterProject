package com.example.twitterapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitterapp.rv_activity.adapters.RVAdapterPosts

class MainActivity : AppCompatActivity() {
    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapterPost: RVAdapterPosts
    private val postList = arrayListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Inicialización del RecyclerView
        rvPosts = findViewById(R.id.rvPosts)
        initRV()
        fillPostList()  // Llenar la lista al iniciar
        rvAdapterPost.notifyDataSetChanged()
    }

    private fun initRV() {
        // Inicialización del adaptador, pasando la lista de posts como parámetro
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
