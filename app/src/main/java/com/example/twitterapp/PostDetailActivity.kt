// PostDetailActivity.kt
package com.example.twitterapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_post)

        // Obtener el post pasado desde la actividad anterior
        val post = intent.getSerializableExtra("EXTRA_POST") as? Post

        // Referencias a las vistas
        val ivProfilePicture: ImageView = findViewById(R.id.ivProfilePicture)
        val tvUserName: TextView = findViewById(R.id.tvUsername)
        val ivPostImage: ImageView = findViewById(R.id.imageView2)
        val ibComments: ImageButton = findViewById(R.id.ibComents)
        val ibLike: ImageButton = findViewById(R.id.ibLike)
        val ibDisplay: ImageButton = findViewById(R.id.ibDisplay)
        val ibSave: ImageButton = findViewById(R.id.ibSave)
        val ibShare: ImageButton = findViewById(R.id.ibShare)


        post?.let {
            tvUserName.text = it.userName
            // Configura los botones si es necesario
            // Por ejemplo, puedes configurar onClickListeners para los botones
            ibComments.setOnClickListener {
                // Acción para el botón de comentarios
            }
            ibLike.setOnClickListener {
                Toast.makeText(this, "Like <3", Toast.LENGTH_SHORT).show()
            }
            ibDisplay.setOnClickListener {
                // Acción para el botón de mostrar
            }
            ibSave.setOnClickListener {
                Toast.makeText(this, "Publicación guardada", Toast.LENGTH_SHORT).show()
            }
            ibShare.setOnClickListener {
                // Acción para el botón de compartir
            }
        }
    }
}
