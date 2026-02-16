package com.example.restaurante.dao

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import com.example.restaurante.models.Plato
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


    //FUNCION A PROBAR

    fun listarPlatos(onResult: (List<Plato>?) -> Unit) {
        val urlListar = EndPoints.Platos.LISTAR

        // EL ERROR: JsonArrayRequest NO recibe el Method.GET al principio
        val request = JsonArrayRequest(urlListar,
            { response ->
                val lista = mutableListOf<Plato>()
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val plato = Plato(
                        item.getString("idplato"),
                        item.getString("descripcion"),
                        item.getDouble("costounitario"),
                        item.getDouble("costofamiliar")
                    )
                    lista.add(plato)
                }
                onResult(lista)
            },
            { error ->
                Log.e("PLATO_DAO", "Error: ${error.message}")
                onResult(null)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }

    fun editarPlato(id: String, descripcion: String, cUni: Double, cFam: Double, onResult: (Boolean) -> Unit) {
        val urlEdit = "${EndPoints.Platos.UPDATE}/$id"

        val jsonBody = JSONObject().apply {
            put("idplato", id)
            put("descripcion", descripcion)
            put("costounitario", cUni)
            put("costofamiliar", cFam)
        }

        val request = JsonObjectRequest(Request.Method.PUT, urlEdit, jsonBody,
            { response ->
                onResult(true)
            },
            { error ->
                Log.e("PLATO_DAO", "Error al editar: ${error.message}")
                onResult(false)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }
}