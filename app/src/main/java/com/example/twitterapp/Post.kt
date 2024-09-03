package com.example.twitterapp

import java.io.Serializable

/**
 * Data class to store app posts
 */
data class Post(
    val userName: String
) : Serializable // Asegúrate de que el modelo sea Serializable
