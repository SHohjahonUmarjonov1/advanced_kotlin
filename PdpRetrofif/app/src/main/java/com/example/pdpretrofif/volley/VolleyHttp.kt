package com.example.pdpretrofif.volley

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.pdpretrofif.MyApp
import com.example.pdpretrofif.model.PostModel
import org.json.JSONObject
import retrofit2.http.Body

class VolleyHttp {
    companion object {
        val TAG = VolleyHttp::class.java.simpleName
        const val IS_TESTER = true
        const val SERVER_DEVELOPMENT = "https://dummy.restapiexample.com/api/v1/"
        const val SERVER_PRODUCTION = "https://dummy.restapiexample.com/api/v1/"
    }

    fun server(url: String): String {
        return if (IS_TESTER) SERVER_DEVELOPMENT + url
        else SERVER_PRODUCTION + url
    }

    fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
        val stringRequest = object : StringRequest(Method.GET, api,
            Response.Listener { response ->
                Log.d(TAG, response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG, error.toString())
                volleyHandler.onError(error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }

    fun post(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
        val stringRequest = object : StringRequest(Method.POST, api,
            Response.Listener {
                Log.d(TAG, it)
                volleyHandler.onSuccess(it)
            },
            Response.ErrorListener {
                Log.d(TAG,it.toString())
                volleyHandler.onError(it.toString())
            }) {
            override fun getBody(): ByteArray {
                return JSONObject(body as Map<*, *>).toString().toByteArray()
            }
        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }

    fun put(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler) {
        val stringRequest = object : StringRequest(Method.PUT, api,
            Response.Listener { response ->
                Log.d(TAG, response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG, error.toString())
                volleyHandler.onError(error.toString())
            }) {
            override fun getBody(): ByteArray {
                return JSONObject(body as Map<*, *>).toString().toByteArray()
            }
        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }

    fun del(api: String, volleyHandler: VolleyHandler) {
        val stringRequest = object : StringRequest(Method.DELETE, api,
            Response.Listener { response ->
                Log.d(TAG, response)
                volleyHandler.onSuccess(response)
            },
            Response.ErrorListener { error ->
                Log.d(TAG, error.toString())
                volleyHandler.onError(error.toString()) }) {

        }
        MyApp.instance!!.addToRequestQueue(stringRequest)
    }


    var API_LIST_POST = "employees"
    var API_SINGLE_POST = "employee/"
    var API_CREATE_POST = "create"
    var API_UPDATE_POST = "update/"
    var API_DELETE_POST = "delete/"


    fun emptyParams(): HashMap<String, String> {
        return HashMap<String, String>()
    }

    fun createParams(post: PostModel): HashMap<String, Any> {
        val params = HashMap<String, Any>()
        params[post.employeeName] = post.employeeName
        params[post.employeeAge.toString()] = post.employeeAge
        params[post.profileImage] = post.profileImage
        params[post.employeeSalary.toString()] = post.employeeSalary
        return params
    }

    fun updateParams(post: PostModel): HashMap<String, Any> {
        val params = HashMap<String, Any>()
        params[post.id.toString()] = post.id
        params[post.employeeName] = post.employeeName
        params[post.employeeAge.toString()] = post.employeeAge
        params[post.profileImage] = post.profileImage
        params[post.employeeSalary.toString()] = post.employeeSalary
        return params
    }
}