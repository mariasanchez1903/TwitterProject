package com.example.twitterapp.rv_activity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twitterapp.Post
import com.example.twitterapp.R

class RVAdapterPosts(
    private var postList: List<Post>,
    private val onPostClick: (Post) -> Unit
) : RecyclerView.Adapter<RVAdapterPosts.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView = itemView.findViewById(R.id.tvUsername)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.tvUserName.text = post.userName
        holder.itemView.setOnClickListener { onPostClick(post) }
    }

    override fun getItemCount(): Int = postList.size

    fun updatePosts(newPosts: List<Post>) {
        postList = newPosts
        notifyDataSetChanged()
    }
}
