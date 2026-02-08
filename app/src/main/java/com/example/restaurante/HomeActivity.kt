package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.restaurante.ui.auth.LoginActivity
import com.example.restaurante.ui.informacion.MapsActivity
import com.example.restaurante.ui.informacion.QuienesSomosActivity
import com.example.restaurante.ui.informacion.VisionMisionActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var menuInferior:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        menuInferior = findViewById(R.id.navegacionBtn)

        //menu inferior
        menuInferior.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_maps -> {
                    val pantallaMaps = Intent(this, MapsActivity::class.java)
                    startActivity(pantallaMaps)
                    finish()
                    true
                }
                R.id.menu_qs -> {
                    val pantallaQS = Intent(this, QuienesSomosActivity::class.java)
                    startActivity(pantallaQS)
                    finish()
                    true
                }
                R.id.menu_mv -> {
                    val pantallaMV = Intent(this, VisionMisionActivity::class.java)
                    startActivity(pantallaMV)
                    finish()
                    true
                }
                R.id.menu_salir -> {
                    val pantallaHome = Intent(this, LoginActivity::class.java)
                    startActivity(pantallaHome)
                    finish()
                    true
                }
                else -> false
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

}