package com.example.restaurante.ui.platos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R
import com.example.restaurante.ui.usuarios.AgregarUsuariosActivity
import com.example.restaurante.ui.usuarios.ListarUsuariosActivity

class GestPlatosActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    lateinit var btnListarPlatos: Button
    lateinit var btnAgregarPlato: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gest_platos)

        toolbar = findViewById(R.id.toolbar)
        btnListarPlatos = findViewById(R.id.btnListarPlatos)
        btnAgregarPlato = findViewById(R.id.btnAgregarPlato)

        btnListarPlatos.setOnClickListener {
            val pantallaListarPlato = Intent(this, ListarPlatosActivity::class.java)
            startActivity(pantallaListarPlato)
            finish()
        }
        btnAgregarPlato.setOnClickListener {
            val pantallaAgregarPlato = Intent(this, AgregarPlatoActivity::class.java)
            startActivity(pantallaAgregarPlato)
            finish()
        }


        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }
}