package com.example.restaurante.ui.platos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.R
import com.example.restaurante.dao.PlatoDao

class EditarPlatoActivity : AppCompatActivity() {

    lateinit var txtCodPlato: EditText
    lateinit var txtDescripcion: EditText
    lateinit var txtCostoUni: EditText
    lateinit var txtCostoFami: EditText
    lateinit var btnActualizar: Button
    lateinit var toolbar: Toolbar

    lateinit var platoDao: PlatoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_plato)

        txtCodPlato = findViewById(R.id.txtCodPlatoEdit)
        txtDescripcion = findViewById(R.id.txtDescripcionPlatoEdit)
        txtCostoUni = findViewById(R.id.txtCostoUniEdit)
        txtCostoFami = findViewById(R.id.txtCostoFamiEdit)

        btnActualizar = findViewById(R.id.btnActualizarPlato)
        toolbar = findViewById(R.id.toolbar)

        platoDao = PlatoDao(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }


        txtCodPlato.setText(intent.getStringExtra("idplato"))
        txtDescripcion.setText(intent.getStringExtra("descripcion"))
        txtCostoUni.setText(intent.getDoubleExtra("costounitario", 0.0).toString())
        txtCostoFami.setText(intent.getDoubleExtra("costofamiliar", 0.0).toString())

        txtCodPlato.isEnabled = false

        btnActualizar.setOnClickListener {
            editarPlato()
        }
    }

    fun editarPlato() {
        val cod = txtCodPlato.text.toString().trim()
        val desc = txtDescripcion.text.toString().trim()
        val uniStr = txtCostoUni.text.toString().trim()
        val famStr = txtCostoFami.text.toString().trim()

        if (cod.isEmpty() || desc.isEmpty() || uniStr.isEmpty() || famStr.isEmpty()) {
            Toast.makeText(this, "Debe completar todos los campos para actualizar", Toast.LENGTH_SHORT).show()
            return
        }

        val uniD = uniStr.toDoubleOrNull() ?: 0.0
        val famD = famStr.toDoubleOrNull() ?: 0.0

        platoDao.editarPlato(cod, desc, uniD, famD) { exito ->
            if (exito) {
                Toast.makeText(this, "Plato actualizado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al intentar actualizar el plato", Toast.LENGTH_SHORT).show()
            }
        }
    }
}