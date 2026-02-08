package com.example.restaurante.ui.mozos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.HomeActivity
import com.example.restaurante.R

class GestMozosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var toolbar: Toolbar

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gest_mozos)


        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }
}