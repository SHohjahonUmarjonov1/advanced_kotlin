package com.example.pdpretrofif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pdpretrofif.model.BaseModel
import com.example.pdpretrofif.model.PostModel
import com.example.pdpretrofif.networking.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getPosts()
        //getPost(post)
        //createPost(post)
        //updatePost(post)
        deletePost(post)
    }

    private val post = PostModel(61, "PDP", 1000, 1, "Profile")
    private fun getPosts() {
        Network.api.getPosts().enqueue(object : Callback<BaseModel<List<PostModel>>> {
            override fun onResponse(
                call: Call<BaseModel<List<PostModel>>>,
                response: Response<BaseModel<List<PostModel>>>
            ) {
                Log.d("Networking", response.body().toString())
            }

            override fun onFailure(call: Call<BaseModel<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Retrofit", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPost(post: PostModel) {
        Network.api.getPost(post.id).enqueue(object : Callback<BaseModel<PostModel>> {
            override fun onResponse(
                call: Call<BaseModel<PostModel>>,
                response: Response<BaseModel<PostModel>>
            ) {
                Log.d("Networking", response.body().toString())
            }

            override fun onFailure(call: Call<BaseModel<PostModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Retrofit", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createPost(post: PostModel) {
        Network.api.createPost(post).enqueue(object : Callback<BaseModel<PostModel>> {
            override fun onResponse(
                call: Call<BaseModel<PostModel>>,
                response: Response<BaseModel<PostModel>>
            ) {
                Log.d("Networking", response.body().toString())
            }

            override fun onFailure(call: Call<BaseModel<PostModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Retrofit", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updatePost(post: PostModel) {
        Network.api.updatePost(post.id, post).enqueue(object : Callback<BaseModel<PostModel>> {
            override fun onResponse(
                call: Call<BaseModel<PostModel>>,
                response: Response<BaseModel<PostModel>>
            ) {
                Log.d("Networking", response.body().toString())
            }

            override fun onFailure(call: Call<BaseModel<PostModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Retrofit", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deletePost(post: PostModel) {
        Network.api.deletePost(post.id).enqueue(object : Callback<BaseModel<Int>> {
            override fun onResponse(
                call: Call<BaseModel<Int>>,
                response: Response<BaseModel<Int>>
            ) {
                Log.d("Networking", response.body().toString())
            }

            override fun onFailure(call: Call<BaseModel<Int>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Retrofit", Toast.LENGTH_SHORT).show()
            }
        })
    }
}