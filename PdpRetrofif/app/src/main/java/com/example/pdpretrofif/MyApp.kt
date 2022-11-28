package com.example.pdpretrofif

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MyApp : Application() {
    companion object {
        var instance:MyApp?=null
    }

    override fun onCreate() {
        super.onCreate()
        if (instance==null) {
            instance=this
        }
    }

    val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue?.add(request)
    }
}
