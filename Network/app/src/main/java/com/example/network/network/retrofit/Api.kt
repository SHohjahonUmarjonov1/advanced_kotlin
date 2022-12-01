package com.example.network.network.retrofit

import com.example.network.model.PostModel
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("posts")
    fun getPosts(): Call<List<PostModel>>

    @GET("posts/{id}")
    fun getSinglePost(@Path("id")id:Int):Call<PostModel>

    @POST("posts")
    fun createPost(@Body postModel: PostModel):Call<PostModel>

    @PUT("posts/{id}")
    fun updatePost(@Body postModel: PostModel):Call<PostModel>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int):Call<PostModel>
 }