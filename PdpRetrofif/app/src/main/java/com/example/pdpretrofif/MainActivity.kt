package com.example.pdpretrofif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pdpretrofif.model.BaseModel
import com.example.pdpretrofif.model.PostModel
import com.example.pdpretrofif.networking.Network
import com.example.pdpretrofif.volley.VolleyHandler
import com.example.pdpretrofif.volley.VolleyHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //getVolleyPosts(post)
        //getVolleySinglePost(post)
        //createVolleyPost(post)
        //updateVolleyPost(post)
        deleteVolleyPost(post)
    }

    private val post = PostModel(61, "PDP", 1000, 1, "Profile")

    //Retrofit
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

    //Volley

    private fun getVolleyPosts(post: PostModel) {
        VolleyHttp().get(
            VolleyHttp().server(VolleyHttp().API_LIST_POST),
            VolleyHttp().emptyParams(),
            object : VolleyHandler {
                override fun onSuccess(success: String?) {

                }

                override fun onError(error: String?) {

                }
            })
    }

    private fun getVolleySinglePost(post: PostModel) {
        VolleyHttp().get(
            VolleyHttp().server(VolleyHttp().API_SINGLE_POST+post.id),
            VolleyHttp().emptyParams(),
            object : VolleyHandler {
                override fun onSuccess(success: String?) {

                }

                override fun onError(error: String?) {

                }
            })
    }

    private fun createVolleyPost(post: PostModel) {
        VolleyHttp().post(
            VolleyHttp().server(VolleyHttp().API_CREATE_POST),
            VolleyHttp().createParams(post),
            object : VolleyHandler {
                override fun onSuccess(success: String?) {

                }

                override fun onError(error: String?) {

                }
            })
    }

    private fun updateVolleyPost(post: PostModel) {
        VolleyHttp().put(
            VolleyHttp().server(VolleyHttp().API_UPDATE_POST+post.id),
            VolleyHttp().updateParams(post),
            object : VolleyHandler {
                override fun onSuccess(success: String?) {

                }

                override fun onError(error: String?) {

                }
            })
    }

    private fun deleteVolleyPost(post: PostModel) {
        VolleyHttp().del(
            VolleyHttp().server(VolleyHttp().API_DELETE_POST+post.id), object : VolleyHandler {
            override fun onSuccess(success: String?) {

            }

            override fun onError(error: String?) {

            }
        })
    }
}