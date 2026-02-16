package com.example.restaurante.dao

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.restaurante.api.EndPoints
import com.example.restaurante.api.VolleySingleton
import com.example.restaurante.models.Mozo
import com.example.restaurante.models.Pedido
import com.example.restaurante.models.Plato
import org.json.JSONObject

class PedidoDao (val context: Context){
    val urlAdd = EndPoints.Pedidos.ADD

    fun registrarPedido(pedido: Pedido, onResult: (Boolean) -> Unit) {
        val jsonBody = JSONObject().apply {
            put("idpedido", pedido.idpedido)
            put("nmesa", pedido.nmesa)
            put("hora", pedido.hora)
            put("cantidad", pedido.cantidad)
            put("observacion", pedido.observacion)
            put("tipoPlato", pedido.tipoPlato)

            put("plato", JSONObject().apply {
                put("idplato", pedido.plato.idplato)
            })

            put("mozo", JSONObject().apply {
                put("dnimozo", pedido.mozo.dnimozo)
            })
        }
            val request = JsonObjectRequest(Request.Method.POST, urlAdd, jsonBody,
                {response ->
                    Log.d("PEDIDO_DAO", "Agregado: $response")
                    onResult(true)
                },
                { error ->
                    Log.e("PEDIDO_DAO", "Error: ${error.message}")
                    onResult(false)
                }
            )
            VolleySingleton.instance?.addToRequestQueue(request)
        }

    fun listarPedidos(onResult: (List<Pedido>?) -> Unit) {
        val url = EndPoints.Pedidos.LISTAR

        val request = JsonArrayRequest(url,
            { response ->
                val lista = mutableListOf<Pedido>()
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)

                    // Extraer el objeto Plato interno
                    val pJson = item.getJSONObject("plato")
                    val plato = Plato(pJson.getString("idplato"), pJson.getString("descripcion"), 0.0, 0.0)

                    // Extraer el objeto Mozo interno
                    val mJson = item.getJSONObject("mozo")
                    val mozo = Mozo(mJson.getString("dnimozo"), mJson.getString("nombre"), "", "", "", "")

                    val pedido = Pedido(
                        item.getString("idpedido"),
                        item.getString("nmesa"),
                        item.getString("hora"),
                        item.getInt("cantidad"),
                        item.getString("observacion"),
                        item.getString("tipoPlato"),
                        plato,
                        mozo
                    )
                    lista.add(pedido)
                }
                onResult(lista)
            },
            { error ->
                Log.e("PEDIDO_DAO", "Error al listar: ${error.message}")
                onResult(null)
            }
        )
        VolleySingleton.instance?.addToRequestQueue(request)
    }


}