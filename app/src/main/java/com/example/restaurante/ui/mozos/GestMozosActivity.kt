package com.example.restaurante.ui.mozos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R
import com.example.restaurante.ui.usuarios.ListarUsuariosActivity

class GestMozosActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var btnListarMozos: Button
    lateinit var btnAgregarMozo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gest_mozos)

        toolbar = findViewById(R.id.toolbar)
        btnListarMozos = findViewById(R.id.btnListarMozos)
        btnAgregarMozo = findViewById(R.id.btnAgregarMozo)

        btnListarMozos.setOnClickListener{
            val pantallaListarMozos = Intent(this, ListarMozosActivity::class.java)
            startActivity(pantallaListarMozos)
        }

        btnAgregarMozo.setOnClickListener {
            val pantallaAgregarMozos = Intent(this, AgregarMozoActivity::class.java)
            startActivity(pantallaAgregarMozos)
        }

        toolbar.setNavigationOnClickListener{
            finish()
        }
    }
}