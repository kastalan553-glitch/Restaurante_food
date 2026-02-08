package com.example.restaurante.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.restaurante.HomeActivity
import com.example.restaurante.R
import com.example.restaurante.ui.main.MainActivity

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
            llamarMain()
        }
    }

    fun llamarHome(){
        val pantallaHome = Intent(this, HomeActivity::class.java)
        startActivity(pantallaHome)
        finish()
    }
    fun llamarMain(){
        val pantallaMain = Intent(this, MainActivity::class.java)
        startActivity(pantallaMain)
        finish()
    }
}