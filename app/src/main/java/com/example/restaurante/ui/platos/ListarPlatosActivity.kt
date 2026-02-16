package com.example.restaurante.ui.platos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restaurante.R
import com.example.restaurante.adapter.PlatoList
import com.example.restaurante.api.EndPoints
import com.example.restaurante.models.Plato
import org.json.JSONArray
import org.json.JSONException

class ListarPlatosActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var platosList: MutableList<Plato>? = null
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_platos)

        toolbar = findViewById(R.id.toolbar)
        listView = findViewById(R.id.listaPlatosVista)
        platosList = mutableListOf()

        cargarPlatos()

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    fun cargarPlatos() {
        val url = EndPoints.Platos.LISTAR

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { s ->
                try {
                    val array = JSONArray(s)
                    for (i in 0 until array.length()) {
                        val objectPlato = array.getJSONObject(i)

                        val plato = Plato(
                            objectPlato.getString("idplato"),
                            objectPlato.getString("descripcion"),
                            objectPlato.getDouble("costounitario"),
                            objectPlato.getDouble("costofamiliar")
                        )
                        platosList?.add(plato)
                    }

                    val adapter = PlatoList(this@ListarPlatosActivity, platosList!!)
                    listView?.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al procesar datos", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(
                    applicationContext,
                    volleyError.message,
                    Toast.LENGTH_LONG
                ).show()
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}