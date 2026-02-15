package com.example.restaurante.dao

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import org.json.JSONObject

class PlatoDao (val context: Context){
    private val urlAdd = EndPoints.Platos.ADD


    fun agregarPlato(idplato:String, descripcion:String, costounitario:
        Double, costofamiliar:Double, onResult: (Boolean)-> Unit){
        val jsonBody = JSONObject().apply {
            put("idplato", idplato)
            put("descripcion", descripcion)
            put("costounitario", costounitario)
            put("costofamiliar", costofamiliar)
        }
        val request = JsonObjectRequest(Request.Method.POST, urlAdd, jsonBody,
            { response ->
                android.util.Log.d("PLATO_DAO", "Agregado: $response")
                onResult(true)
            },
            { error ->
                android.util.Log.e("PLATO_DAO", "Error: ${error.message}")
                onResult(false)

            }
            )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
    fun eliminarPlato(id: String, onResult: (Boolean) -> Unit) {
        val url = "${EndPoints.Platos.DELETE}/$id"

        val request = com.android.volley.toolbox.StringRequest(
            com.android.volley.Request.Method.DELETE, url,
            { onResult(true) },
            { onResult(false) }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}