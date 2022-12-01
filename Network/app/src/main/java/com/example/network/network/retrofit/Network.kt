package com.example.network.network.retrofit

import com.example.volley.network.volley.VolleyHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Network {

    const val IS_TESTER=true
    const val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
    const val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"

    fun server():String {
        if (VolleyHttp.IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }
    var API_LIST_POST = "posts"
    var API_SINGLE_POST = "posts/"
    var API_CREATE_POST = "posts"
    var API_UPDATE_POST = "posts/"
    var API_DELETE_POST = "posts/"

    private val interceptor = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api = retrofit.create(Api::class.java)
}