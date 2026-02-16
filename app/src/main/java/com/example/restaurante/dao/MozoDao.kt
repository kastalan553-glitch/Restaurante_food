package com.example.restaurante.dao

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import com.example.restaurante.models.Mozo
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

    fun listarMozos(onResult: (List<Mozo>?) -> Unit) {
        val urlListar = EndPoints.Mozos.LISTAR

        val request = JsonArrayRequest(urlListar,
            { response ->
                val lista = mutableListOf<Mozo>()
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    // Creamos el objeto Mozo con los 6 parámetros de tu modelo
                    val mozo = Mozo(
                        item.optString("dnimozo"),
                        item.optString("nombre"),
                        item.optString("direccion"),
                        item.optString("fechaingreso"),
                        item.optString("movil"),
                        item.optString("email")
                    )
                    lista.add(mozo)
                }
                onResult(lista)
            },
            { error ->
                android.util.Log.e("MOZO_DAO", "Error: ${error.message}")
                onResult(null)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }

    fun eliminarMozo(id: String, onResult: (Boolean) -> Unit) {
        val url = "${EndPoints.Mozos.DELETE}/$id"

        val request = com.android.volley.toolbox.StringRequest(
            com.android.volley.Request.Method.DELETE, url,
            { onResult(true) },
            { onResult(false) }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}