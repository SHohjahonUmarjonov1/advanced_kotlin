package com.example.volley.network.volley

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.network.MyApp
import com.example.network.model.PostModel
import com.example.network.network.volley.VolleyHandler
import org.json.JSONObject

class VolleyHttp {
    companion object {
        val TAG = VolleyHttp::class.java.simpleName
        const val IS_TESTER = true
        const val SERVER_DEVELOPMENT = "https://jsonplaceholder.typicode.com/"
        const val SERVER_PRODUCTION = "https://jsonplaceholder.typicode.com/"
    }
    fun server(url:String):String {
        if (IS_TESTER) return SERVER_DEVELOPMENT+url
        return SERVER_PRODUCTION+url
    }
    var API_LIST_POST = "posts"
    var API_SINGLE_POST = "posts/"
    var API_CREATE_POST = "posts"
    var API_UPDATE_POST = "posts/"
    var API_DELETE_POST = "posts/"

    fun emptyList():HashMap<String,String> {
        return HashMap<String,String>()
    }

    fun createList(post:PostModel):HashMap<String,Any> {
        val list=HashMap<String,Any>()
        list.put(post.title,post.title)
        list.put(post.body,post.body)
        list.put(post.userId.toString(),post.userId)
        return list
    }

    fun updateList(post: PostModel):HashMap<String,Any> {
        val list=HashMap<String,Any>()
        list.put(post.id.toString(),post.id)
        list.put(post.title,post.title)
        list.put(post.body,post.body)
        list.put(post.userId.toString(),post.userId)
        return list
    }
    fun get(api:String, volleyHandler: VolleyHandler) {
        val stringRequest=object : StringRequest(
            Method.GET,api,Response.Listener { response ->
                Log.d(TAG,response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG,error.toString())
                volleyHandler.onError(error.toString())
            }
        ) {

        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }

    fun post(api:String,body: HashMap<String,Any>,volleyHandler: VolleyHandler) {
        val stringRequest=object :StringRequest(Method.POST,api,
            Response.Listener { response ->
                Log.d(TAG,response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG,error.toString())
                volleyHandler.onError(error.toString())
            }) {
            override fun getBody(): ByteArray {
                return JSONObject(body as Map<*,*>).toString().toByteArray()
            }
        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }
    fun put(api:String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
        val stringRequest=object :StringRequest(Method.PUT,api,
            Response.Listener { response ->
                Log.d(TAG,response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG,error.toString())
                volleyHandler.onError(error.toString())
            }) {
            override fun getBody(): ByteArray {
                return JSONObject(body as Map<*,*>).toString().toByteArray()
            }
        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }
    fun delete(api:String, volleyHandler: VolleyHandler) {
        val stringRequest=object :StringRequest(Method.DELETE,api,
            Response.Listener { response ->
                Log.d(TAG,response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG,error.toString())
                volleyHandler.onError(error.toString())
            }) {

        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }
}