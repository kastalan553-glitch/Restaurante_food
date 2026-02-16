package com.example.restaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.adapter.PedidoList
import com.example.restaurante.dao.PedidoDao

class ListaPedidosActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pedidos)

        listView = findViewById(R.id.listaPedidosVista)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener { finish() }

        cargarPedidosServidor()
    }
    fun cargarPedidosServidor() {
        PedidoDao(this).listarPedidos { lista ->
            if (lista != null) {
                val adapter = PedidoList(this, lista)
                listView.adapter = adapter
            } else {
                Toast.makeText(this, "No se encontraron pedidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}