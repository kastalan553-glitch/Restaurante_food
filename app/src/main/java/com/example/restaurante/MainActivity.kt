package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
            finish()
        }

    }

    fun llamarLogin(){
        val pantallaLogin = Intent(this, LoginActivity::class.java)
        startActivity(pantallaLogin)
        finish()
    }

}