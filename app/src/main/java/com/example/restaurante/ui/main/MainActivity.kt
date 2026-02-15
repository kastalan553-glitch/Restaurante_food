package com.example.restaurante.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.restaurante.ui.auth.LoginActivity
import com.example.restaurante.R

class MainActivity : AppCompatActivity() {
    lateinit var btnCerrar: Button
    lateinit var btnContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCerrar = findViewById(R.id.btnCerrarApp)
        btnContinuar = findViewById(R.id.btnContinuar)

        //Eventos
        btnContinuar.setOnClickListener {
            llamarLogin()
        }

        btnCerrar.setOnClickListener {
            cerrar()
        }

    }
    fun cerrar(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Una vez finalizada la App tendras que volver a iniciar sesión")
            .setTitle("¿Seguro que deseas salir?")
            .setPositiveButton(android.R.string.yes){ dialog, which ->
                Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                System.exit(0)
            }
            .setNegativeButton(android.R.string.no){dialog, which ->
                Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun llamarLogin(){
        val pantallaLogin = Intent(this, LoginActivity::class.java)
        startActivity(pantallaLogin)
        finish()
    }

}