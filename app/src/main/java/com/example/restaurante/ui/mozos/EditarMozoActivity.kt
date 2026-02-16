package com.example.restaurante.ui.mozos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.R
import com.example.restaurante.dao.MozoDao

class EditarMozoActivity : AppCompatActivity() {

    lateinit var txtDni: EditText
    lateinit var txtNombre: EditText
    lateinit var txtDireccion: EditText
    lateinit var txtFechaIngreso: EditText
    lateinit var txtMovil: EditText
    lateinit var txtEmail: EditText

    lateinit var btnActualizar: Button
    lateinit var toolbar: Toolbar
    lateinit var mozoDao: MozoDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mozo)

        txtDni = findViewById(R.id.txtDniMozoEdit)
        txtNombre = findViewById(R.id.txtNombreMozoEdit)
        txtDireccion = findViewById(R.id.txtDireccionMozoEdit)
        txtFechaIngreso = findViewById(R.id.txtFechaIngresoMozoEdit)
        txtMovil = findViewById(R.id.txtMovilMozoEdit)
        txtEmail = findViewById(R.id.txtEmailMozoEdit)

        btnActualizar = findViewById(R.id.btnUpdateMozo)
        toolbar = findViewById(R.id.toolbar)

        mozoDao = MozoDao(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }



        txtDni.setText(intent.getStringExtra("dnimozo"))
        txtNombre.setText(intent.getStringExtra("nombre"))
        txtDireccion.setText(intent.getStringExtra("direccion"))
        txtFechaIngreso.setText(intent.getStringExtra("fechaingreso"))
        txtMovil.setText(intent.getStringExtra("movil"))
        txtEmail.setText(intent.getStringExtra("email"))

        txtDni.isEnabled = false

        btnActualizar.setOnClickListener {
            actualizarDatos()
        }


    }

    fun actualizarDatos() {

        val Mdni = txtDni.text.toString().trim()
        val nombre = txtNombre.text.toString().trim()
        val direccion = txtDireccion.text.toString().trim()
        val fechaingreso = txtFechaIngreso.text.toString().trim()
        val movil = txtMovil.text.toString().trim()
        val email = txtEmail.text.toString().trim()

        if (Mdni.isEmpty() || nombre.isEmpty() || direccion.isEmpty() ||
            fechaingreso.isEmpty() || movil.isEmpty() || email.isEmpty()) {

            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        mozoDao.editarMozo(Mdni, nombre, direccion, fechaingreso, movil, email) { exito ->

            if (exito) {
                Toast.makeText(this, "Mozo actualizado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al actualizar mozo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
