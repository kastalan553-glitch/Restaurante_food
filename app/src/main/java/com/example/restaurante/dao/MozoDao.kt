package com.example.restaurante.dao

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import org.json.JSONObject

class MozoDao (val context: Context) {
    private val urlAdd = EndPoints.Mozos.ADD

    fun agregarMozo(dni: String, nombre: String, direccion: String, fecha: String, movil: String,
                    email: String, onResult: (Boolean) -> Unit) {
        val jsonBody = JSONObject().apply {
            put("dnimozo", dni)
            put("nombre", nombre)
            put("direccion", direccion)
            put("fechaingreso", fecha)
            put("movil", movil)
            put("email", email)
        }
        val request = JsonObjectRequest(Request.Method.POST, urlAdd, jsonBody,
            { response ->
                Log.d("MOZO_DAO", "Agregado: $response")
                onResult(true)
            },
            { error ->
                Log.e("MOZO_DAO", "Error: ${error.message}")
                onResult(false)
            }
            )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}