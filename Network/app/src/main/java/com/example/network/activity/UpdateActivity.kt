package com.example.network.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.network.databinding.ActivityUpdateBinding
import com.example.network.model.PostModel
import com.example.network.network.retrofit.Network
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews() {
        var json = intent.getStringExtra("post")
        var post=Gson().fromJson(json,PostModel::class.java)
        binding.title.setText(post.title)
        binding.body.setText(post.body)
        post = PostModel(binding.body.text.toString(), post.id, binding.title.text.toString(), post.userId)
        binding.addPost.setOnClickListener {
            if (binding.title.text.toString().trim().isEmpty() || binding.body.text.toString()
                    .trim().isEmpty()
            ) {
                Toast.makeText(this, "Yuqoridagi maydonlarni to'liq to'ldiring", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
        updateList(post)
        finish()
        }
    }
    fun updateList(post:PostModel) {
        binding.progress.isVisible=true
        binding.linear.isVisible=false
        Network.api.updatePost(post).enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                binding.progress.isVisible=false
                Toast.makeText(this@UpdateActivity, "Success Update", Toast.LENGTH_SHORT).show()
                EventBus.getDefault().post(post)
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                Toast.makeText(this@UpdateActivity, "Error Update", Toast.LENGTH_SHORT).show()
            }
        })
    }
}