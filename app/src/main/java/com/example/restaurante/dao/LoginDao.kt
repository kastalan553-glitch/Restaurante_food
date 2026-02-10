package com.example.restaurante.dao

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import com.example.restaurante.models.Login
import org.json.JSONObject

class LoginDao(private val context: Context) {

    private val url = EndPoints.Usuarios.LOGIN

    fun login(credenciales: Login, onResult: (Boolean) -> Unit) {

        val jsonBody = JSONObject().apply {
            put("nombre", credenciales.nombre)
            put("clave", credenciales.clave)
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonBody,
            { response ->

                onResult(true)
            },
            { error ->

                onResult(false)
            }
        )

        VolleySingleton.instance?.addToRequestQueue(request)
    }
}