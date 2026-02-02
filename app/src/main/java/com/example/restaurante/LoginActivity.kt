package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var btnCerrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin = findViewById(R.id.btnLogin)
        btnCerrar = findViewById(R.id.btnCerrarLogin)

        btnLogin.setOnClickListener {
            llamarHome()
        }
        btnCerrar.setOnClickListener {
            finish()
        }
    }

    fun llamarHome(){
        val pantallaHome = Intent(this, HomeActivity::class.java)
        startActivity(pantallaHome)
        finish()
    }
}