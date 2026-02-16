package com.example.restaurante.ui.usuarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.restaurante.R
import com.example.restaurante.dao.UsuarioDao
import com.example.restaurante.ui.platos.GestPlatosActivity

class EditarUsuarioActivity : AppCompatActivity() {
    lateinit var txtNombre: EditText
    lateinit var txtClave: EditText
    lateinit var btnActualizar: Button
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var usuarioDao: UsuarioDao

    var idUsuario: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)
        txtNombre = findViewById(R.id.txtNomUsuEdit)
        txtClave = findViewById(R.id.txtPassUsuEdit)
        btnActualizar = findViewById(R.id.btnUpdateUsu)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener{
            finish()
        }

        usuarioDao = UsuarioDao(this)

        idUsuario = intent.getIntExtra("id", -1)
        val nombreActual = intent.getStringExtra("nombre")
        val claveActual = intent.getStringExtra("clave")

        txtNombre.setText(nombreActual)
        txtClave.setText(claveActual)

        btnActualizar.setOnClickListener {
            actualizarDatos()
        }
    }
    fun actualizarDatos() {
        val nombre = txtNombre.text.toString().trim()
        val clave = txtClave.text.toString().trim()

        if (nombre.isEmpty() || clave.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        usuarioDao.editarUsuario(idUsuario, nombre, clave) { exito ->
            if (exito) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }

    }
}