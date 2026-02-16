package com.example.restaurante.ui.mozos

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
import com.example.restaurante.adapter.MozoList
import com.example.restaurante.api.EndPoints
import com.example.restaurante.models.Mozo
import org.json.JSONArray
import org.json.JSONException

class ListarMozosActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var mozosList: MutableList<Mozo>? = null
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_mozos)

        toolbar = findViewById(R.id.toolbar)
        listView = findViewById(R.id.listaPedidosVista)
        mozosList = mutableListOf()

        cargarMozos()

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun cargarMozos() {
        val url = EndPoints.Mozos.LISTAR

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { s ->
                try {
                    val array = JSONArray(s)
                    for (i in 0 until array.length()) {
                        val objectMozo = array.getJSONObject(i)

                        val mozo = Mozo(
                            objectMozo.getString("dnimozo"),
                            objectMozo.getString("nombre"),
                            objectMozo.getString("direccion"),
                            objectMozo.getString("fechaingreso"),
                            objectMozo.getString("movil"),
                            objectMozo.getString("email")
                        )
                        mozosList?.add(mozo)
                    }

                    val adapter = MozoList(this@ListarMozosActivity, mozosList!!)
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