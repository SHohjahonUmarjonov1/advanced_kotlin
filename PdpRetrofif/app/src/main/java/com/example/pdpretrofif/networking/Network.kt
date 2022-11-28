package com.example.pdpretrofif.networking

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    const val IS_TESTER=true
    const val SERVER_DEVELOPMENT="https://dummy.restapiexample.com/api/v1/"
    const val SERVER_PRODUCTION="https://dummy.restapiexample.com/api/v1/"

    private fun server():String {
        return if (IS_TESTER) SERVER_DEVELOPMENT
        else SERVER_PRODUCTION
    }

    private val interceptor=HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client=OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit=Retrofit.Builder()
        .baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api= retrofit.create(Api::class.java)

}