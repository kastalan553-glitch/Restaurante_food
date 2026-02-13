package com.example.restaurante.dao

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import org.json.JSONObject

class UsuarioDao (private val context: Context){

    private val url = EndPoints.Usuarios.ADD

    fun agregarUsuario(nombre: String, clave: String, onResult: (Boolean) -> Unit){
        val jsonBody = JSONObject().apply {
            put("nombre", nombre)
            put("clave", clave)
        }
        val request = JsonObjectRequest(Request.Method.POST, url, jsonBody,
            { response ->
                android.util.Log.d("USUARIO_DAO", "Agregado: $response")
                onResult(true)
            },
            { error ->
                android.util.Log.e("USUARIO_DAO", "Error: ${error.message}")
                onResult(false)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}