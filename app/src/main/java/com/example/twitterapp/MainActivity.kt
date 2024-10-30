package com.example.twitterapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitterapp.rv_activity.adapters.RVAdapterPosts
import com.example.twitterapp.viewModel.TwitterUIState
import com.example.twitterapp.viewModel.TwitterViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var rvPosts: RecyclerView
    private lateinit var rvAdapterPost: RVAdapterPosts
    private val twitterViewModel: TwitterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }


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
@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_user),
                contentDescription = "User Icon",
                modifier = Modifier.size(53.dp, 36.dp),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = "App Logo",
                modifier = Modifier.size(54.dp, 37.dp)
            )

            IconButton(onClick = { /* Acción de configuración */ }) {
                Icon(
                    painter = painterResource(id = R.mipmap.ic_settings),
                    contentDescription = "Settings Icon"
                )
            }
        }

        // Botones "For You" y "Following"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { /* Acción de For You */ },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "For You", color = Color.Black)
            }

            Button(
                onClick = { /* Acción de Following */ },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Following", color = Color.Black)
            }
        }

        // Lista de publicaciones (RecyclerView convertido a LazyColumn)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10) { index ->  // Aquí enlaza tus datos reales
                // Aquí iría el diseño de cada elemento de la lista
                Text(text = "Post #$index")
            }
        }
    }
}

