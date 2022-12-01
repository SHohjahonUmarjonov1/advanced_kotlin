package com.example.network.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.network.activity.MainActivity
import com.example.network.databinding.PostItemsBinding
import com.example.network.model.PostModel

class PostAdapter(val activity: MainActivity, private val posts: Array<PostModel>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: PostItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostModel, position: Int) {
            binding.title.text = post.title.toUpperCase()
            binding.body.text = post.body
            binding.root.setOnLongClickListener {
                activity.deleteDialog(post)
                return@setOnLongClickListener false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PostItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position], position)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}