package com.example.restaurante.ui.informacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R

class QuienesSomosActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quienes_somos)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }
}