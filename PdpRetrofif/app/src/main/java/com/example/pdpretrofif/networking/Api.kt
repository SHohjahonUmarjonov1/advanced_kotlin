package com.example.pdpretrofif.networking

import com.example.pdpretrofif.model.BaseModel
import com.example.pdpretrofif.model.PostModel
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("employees")
    fun getPosts(): Call<BaseModel<List<PostModel>>>

    @GET("employee/{id}")
    fun getPost(@Path("id") id: Int): Call<BaseModel<PostModel>>

    @POST("create")
    fun createPost(@Body postModel: PostModel): Call<BaseModel<PostModel>>

    @PUT("update/{id}")
    fun updatePost(@Path("id") id: Int, @Body postModel: PostModel): Call<BaseModel<PostModel>>

    @DELETE("delete/{id}")
    fun deletePost(@Path("id") id: Int): Call<BaseModel<Int>>
}