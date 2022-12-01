package com.example.network.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.network.R
import com.example.network.databinding.ActivityCreatePostBinding
import com.example.network.model.PostModel
import com.example.network.network.volley.VolleyHandler
import com.example.volley.network.volley.VolleyHttp
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val post=PostModel(binding.body.text.toString(),1,binding.title.text.toString(),100)
        binding.addPost.setOnClickListener {
            if (binding.title.text.toString().trim().isEmpty() || binding.body.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Yuqoridagi maydonlarni to'liq to'ldiring", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                createPost(post)

        }
    }
    private fun createPost(post: PostModel) {
        binding.progress.isVisible=true
        binding.linear.isVisible=false
        VolleyHttp().post(
            VolleyHttp().server(VolleyHttp().API_CREATE_POST),
            VolleyHttp().createList(post),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@CreatePostActivity, "Success create post", Toast.LENGTH_SHORT).show()
                    EventBus.getDefault().post(post)
                }
                override fun onError(error: String?) {
                    binding.progress.isVisible = false

                    Toast.makeText(this@CreatePostActivity, "Error create post", Toast.LENGTH_SHORT).show()
                }
            })
        finish()
    }

}