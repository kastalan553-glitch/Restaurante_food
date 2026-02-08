package com.example.restaurante.ui.informacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R

class MapsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    //botones
    lateinit var btnlocal1: Button
    lateinit var btnlocal2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        toolbar = findViewById(R.id.toolbar)
        btnlocal1 = findViewById(R.id.btnLocal1)
        btnlocal2 = findViewById(R.id.btnLocal2)

        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }
}