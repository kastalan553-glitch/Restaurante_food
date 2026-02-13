package com.example.restaurante.ui.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import com.example.restaurante.R
import com.example.restaurante.dao.UsuarioDao

class AgregarUsuariosActivity : AppCompatActivity() {
    lateinit var txtNombre : EditText
    lateinit var txtPassUsu : EditText
    lateinit var btnRegistrarUsu:Button
    lateinit var usuarioDao: UsuarioDao
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuarios)

        txtNombre = findViewById(R.id.txtNomUsu)
        txtPassUsu = findViewById(R.id.txtPassUsu)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener{
            val pantallaGestUsu = Intent(this, GestUsuariosActivity::class.java)
            startActivity(pantallaGestUsu)
            finish()
        }

        usuarioDao = UsuarioDao(this)

        btnRegistrarUsu = findViewById(R.id.btnAddUsu)

        btnRegistrarUsu.setOnClickListener {
            registrarUsuario()
        }
    }
    fun registrarUsuario() {
        val nom = txtNombre.text.toString().trim()
        val cla = txtPassUsu.text.toString().trim()

        if (nom.isEmpty() || cla.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        usuarioDao.agregarUsuario(nom, cla) { exito ->
            if (exito) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}