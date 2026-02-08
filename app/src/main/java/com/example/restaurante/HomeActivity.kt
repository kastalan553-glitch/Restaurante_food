package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.restaurante.ui.auth.LoginActivity
import com.example.restaurante.ui.informacion.MapsActivity
import com.example.restaurante.ui.informacion.QuienesSomosActivity
import com.example.restaurante.ui.informacion.VisionMisionActivity
import com.example.restaurante.ui.mozos.GestMozosActivity
import com.example.restaurante.ui.platos.GestPlatosActivity
import com.example.restaurante.ui.usuarios.GestUsuariosActivity
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


    //PARA AÑADIR EL MENU SUPERIOR AL HOME
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //PARA QUE LAS OPCIONES DEL MENU SUPERIOR FUNCIONEN PS
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.gestionUsuarios -> {
                val pantallaGestUsu = Intent(this, GestUsuariosActivity::class.java)
                startActivity(pantallaGestUsu)
            }
            R.id.gestionMozos -> {
                val pantallaGestMoz = Intent(this, GestMozosActivity::class.java)
                startActivity(pantallaGestMoz)
            }
            R.id.gestionPlatos -> {
                val pantallaGestPlat = Intent(this, GestPlatosActivity::class.java)
                startActivity(pantallaGestPlat)
            }
            R.id.gestionPedidos -> {
                val pantallaGestPed = Intent(this, GestPedidosActivity::class.java)
                startActivity(pantallaGestPed)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}