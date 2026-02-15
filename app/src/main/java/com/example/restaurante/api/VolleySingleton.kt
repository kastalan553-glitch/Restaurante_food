package com.example.restaurante.api

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(applicationContext)
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }

    companion object {
        @get:Synchronized
        var instance: VolleySingleton? = null
            private set
    }
}