package com.example.restaurante.dao

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import org.json.JSONObject

class UsuarioDao (private val context: Context){

    private val urlAdd = EndPoints.Usuarios.ADD
    private val urlDelete = EndPoints.Usuarios.DELETE

    fun agregarUsuario(nombre: String, clave: String, onResult: (Boolean) -> Unit){
        val jsonBody = JSONObject().apply {
            put("nombre", nombre)
            put("clave", clave)
        }
        val request = JsonObjectRequest(Request.Method.POST, urlAdd, jsonBody,
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

    fun eliminarUsuario(id: Int, onResult: (Boolean) -> Unit){
        val urlDeteleId = "$urlDelete/$id"

        val request = com.android.volley.toolbox.StringRequest(
            Request.Method.DELETE,
            urlDeteleId,
            { response ->
                android.util.Log.d("USUARIO_DAO", "Eliminado exitosamente: $id")
                onResult(true)
            },
            { error ->
                android.util.Log.e("USUARIO_DAO", "Error al eliminar $id: ${error.message}")
                onResult(false)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}