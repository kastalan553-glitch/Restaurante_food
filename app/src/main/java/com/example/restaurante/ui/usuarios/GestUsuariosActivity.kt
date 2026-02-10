package com.example.restaurante.ui.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R

class GestUsuariosActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    lateinit var btnListarUsuarios: Button
    lateinit var btnAgregarUsuario: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gest_usuarios)

        toolbar = findViewById(R.id.toolbar)
        btnListarUsuarios = findViewById(R.id.btnListarUsuarios)
        btnAgregarUsuario = findViewById(R.id.btnAgregarUsuario)

        btnListarUsuarios.setOnClickListener {
            val pantallaListarUsu = Intent(this, ListarUsuariosActivity::class.java)
            startActivity(pantallaListarUsu)
        }

        btnAgregarUsuario.setOnClickListener {
                val pantallaAgregarUsu = Intent(this, AgregarUsuariosActivity::class.java)
            startActivity(pantallaAgregarUsu)
        }

        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }
}