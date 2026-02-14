package com.example.restaurante.ui.mozos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.R
import com.example.restaurante.dao.MozoDao
import com.example.restaurante.ui.platos.GestPlatosActivity

class AgregarMozoActivity : AppCompatActivity() {
    lateinit var txtDni: EditText
    lateinit var txtNombre: EditText
    lateinit var txtDireccion: EditText
    lateinit var txtFecha: EditText
    lateinit var txtMovil: EditText
    lateinit var txtEmail: EditText

    lateinit var btnRegistrar: Button
    lateinit var toolbar: Toolbar

    lateinit var mozoDao: MozoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mozo)

        txtDni = findViewById(R.id.txtDniMozo)
        txtNombre = findViewById(R.id.txtNomMozo)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtFecha = findViewById(R.id.txtFechaIngreso)
        txtMovil = findViewById(R.id.txtMovilMozo)
        txtEmail = findViewById(R.id.txtEmailMozo)
        btnRegistrar = findViewById(R.id.btnAgregarMozo)
        toolbar = findViewById(R.id.toolbar)

        mozoDao = MozoDao(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        btnRegistrar.setOnClickListener {
            registrarMozo()
        }
    }
    fun registrarMozo() {
        val dni = txtDni.text.toString().trim()
        val nom = txtNombre.text.toString().trim()
        val dir = txtDireccion.text.toString().trim()
        val fec = txtFecha.text.toString().trim()
        val mov = txtMovil.text.toString().trim()
        val ema = txtEmail.text.toString().trim()

        if (dni.isEmpty() || nom.isEmpty() || dir.isEmpty() || fec.isEmpty() || mov.isEmpty() || ema.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Llamamos al DAO (lo crearemos en el siguiente paso)
        mozoDao.agregarMozo(dni, nom, dir, fec, mov, ema) { exito ->
            if (exito) {
                Toast.makeText(this, "Mozo registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar mozo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}