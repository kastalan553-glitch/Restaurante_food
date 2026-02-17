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

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        btnListar = findViewById(R.id.btnListarPedidos)
        btnListar.setOnClickListener {
            val intent = Intent(this, ListaPedidosActivity::class.java)
            startActivity(intent)
        }
    }
 }