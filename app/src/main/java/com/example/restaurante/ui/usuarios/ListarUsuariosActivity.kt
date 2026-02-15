package com.example.restaurante.ui.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restaurante.R
import com.example.restaurante.adapter.UsuarioList
import com.example.restaurante.api.EndPoints
import com.example.restaurante.models.Usuario
import org.json.JSONArray
import org.json.JSONException

class ListarUsuariosActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var userList: MutableList<Usuario>? = null
    lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_usuarios)
        toolbar = findViewById(R.id.toolbar)

        listView = findViewById(R.id.listaUsuariosVista)
        userList = mutableListOf()
        cargarUsuarios()

        toolbar.setNavigationOnClickListener {
            val pantallaGestUsu = Intent(this, GestUsuariosActivity::class.java)
            startActivity(pantallaGestUsu)
            finish()
        }
    }

    private fun cargarUsuarios() {

        val url = EndPoints.Usuarios.LISTAR

        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { s ->
                try {
                    //userList?.clear()
                    val array = JSONArray(s)
                    for (i in 0 until array.length()) {
                        val objectUsuario = array.getJSONObject(i)

                        val usuario = Usuario(
                            objectUsuario.getInt("id"),
                            objectUsuario.getString("nombre"),
                            objectUsuario.getString("clave")
                        )

                        userList?.add(usuario)
                    }

                    val adapter = UsuarioList(this@ListarUsuariosActivity, userList!!)
                    listView?.adapter = adapter

                }catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al procesar datos", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

}