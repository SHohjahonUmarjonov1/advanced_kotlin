package com.example.network.network.volley

interface VolleyHandler {
    fun onSuccess(response:String?)
    fun onError(error: String?)
}