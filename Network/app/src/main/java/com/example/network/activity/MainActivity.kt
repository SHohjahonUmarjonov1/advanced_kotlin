package com.example.network.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.adapter.PostAdapter
import com.example.network.databinding.ActivityMainBinding
import com.example.network.model.PostModel
import com.example.network.network.retrofit.Network
import com.example.network.network.volley.VolleyHandler
import com.example.volley.network.volley.VolleyHttp
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    companion object {
        private var mainActivity: MainActivity? = null
        fun getInstance(): MainActivity? {
            if (mainActivity == null) {
                mainActivity = MainActivity()
            }
            return mainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        initViews()
    }

    private fun initViews() {
        binding.progress.isVisible = true
        getList()
        binding.add.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }

    private val post = PostModel("PDP Academy Best Great", 1, "PDP Academy", 100)

    private fun refreshAdapter(posts: Array<PostModel>) {
        binding.recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = PostAdapter(this, posts)
    }

    fun deleteDialog(post: PostModel) {
        val dialog = AlertDialog.Builder(binding.root.context)
            .setTitle("Dialog")
            .setMessage("Do you want to delete this post?")
            .setPositiveButton("Ok") { dialog, _ ->
                deletePost(post)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    //Volley
    fun getPost() {
        binding.progress.isVisible=true
        VolleyHttp().get(VolleyHttp().server(VolleyHttp().API_LIST_POST), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                val posts = Gson().fromJson(response, Array<PostModel>::class.java)
                refreshAdapter(posts)
                binding.progress.isVisible = false
            }

            override fun onError(error: String?) {
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                binding.progress.isVisible = false
            }
        })
    }

    private fun getSinglePost(post: PostModel) {
        binding.progress.isVisible=true
        VolleyHttp().get(
            VolleyHttp().server(VolleyHttp().API_SINGLE_POST + post.id),
            object : VolleyHandler {

                override fun onSuccess(response: String?) {
                    val post = Gson().fromJson(response, PostModel::class.java)
                    binding.progress.isVisible = false
                }

                override fun onError(error: String?) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    binding.progress.isVisible = false
                }
            })
    }

    private fun createPost(post: PostModel) {
        binding.progress.isVisible=true
        VolleyHttp().post(
            VolleyHttp().server(VolleyHttp().API_CREATE_POST),
            VolleyHttp().createList(post),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@MainActivity, "Success create post", Toast.LENGTH_SHORT).show()
                    getPost()
                }

                override fun onError(error: String?) {
                    Toast.makeText(this@MainActivity, "Error create post", Toast.LENGTH_SHORT).show()
                    binding.progress.isVisible = false
                }
            })
    }

    private fun updatePost(post: PostModel) {
        binding.progress.isVisible=true
        VolleyHttp().put(
            VolleyHttp().server(VolleyHttp().API_UPDATE_POST + post.id),
            VolleyHttp().updateList(post),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@MainActivity, "Success update post", Toast.LENGTH_SHORT).show()
                    getPost()
                }

                override fun onError(error: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@MainActivity, "Error create post", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun deletePost(post: PostModel) {
        binding.progress.isVisible=true
        VolleyHttp().delete(VolleyHttp().server(VolleyHttp().API_DELETE_POST + post.id),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@MainActivity, "Success delete post", Toast.LENGTH_SHORT).show()
                    getPost()
                }

                override fun onError(error: String?) {
                    binding.progress.isVisible = false
                    Toast.makeText(this@MainActivity, "Error create post", Toast.LENGTH_SHORT).show()
                }
            })
    }

    //Retrofit

    fun getList() {
        binding.progress.isVisible=true
        Network.api.getPosts().enqueue(object :Callback<List<PostModel>>{
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                response.body()?.let { refreshAdapter(it.toTypedArray()) }
                binding.progress.isVisible = false
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error List", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getSingleList(post: PostModel) {
        Network.api.getSinglePost(post.id).enqueue(object :Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {

            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {

            }
        })
    }

    fun createList(post: PostModel) {
        Network.api.createPost(post).enqueue(object :Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {

            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {

            }
        })
    }

    fun updateList(post:PostModel) {
        Network.api.updatePost(post).enqueue(object :Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {

            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {

            }
        })
    }

    fun deleteList(post: PostModel) {
        Network.api.deletePost(post.id).enqueue(object :Callback<PostModel>{
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {

            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun getEventBus(post: PostModel) {
        getList()
    }
}