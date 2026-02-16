 package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

 class GestPedidosActivity : AppCompatActivity() {
     lateinit var toolbar: Toolbar
     lateinit var btnListar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gest_pedidos)

        // 1. Configurar Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }

        // 2. Configurar Botón para ir al listado
        btnListar = findViewById(R.id.btnListarPedidos)
        btnListar.setOnClickListener {
            // Reemplaza 'ListarPedidosActivity' con el nombre exacto que le diste a tu clase de listado
            val intent = Intent(this, ListaPedidosActivity::class.java)
            startActivity(intent)
        }
    }
 }