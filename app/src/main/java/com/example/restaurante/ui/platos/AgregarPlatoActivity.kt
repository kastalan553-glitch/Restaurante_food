package com.example.restaurante.ui.platos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.R
import com.example.restaurante.dao.PlatoDao
import com.example.restaurante.ui.usuarios.GestUsuariosActivity

class AgregarPlatoActivity : AppCompatActivity() {
    lateinit var txtCodPlato: EditText
    lateinit var txtDescripcion: EditText
    lateinit var txtCostoUni: EditText
    lateinit var txtCostoFami: EditText

    lateinit var btnAgregarPlato: Button

    lateinit var toolbar: Toolbar

    lateinit var platoDao: PlatoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_plato)
        txtCodPlato = findViewById(R.id.txtCodPlato)
        txtDescripcion = findViewById(R.id.txtDescripcionPlato)
        txtCostoUni = findViewById(R.id.txtCostoUni)
        txtCostoFami = findViewById(R.id.txtCostoFami)
        btnAgregarPlato = findViewById(R.id.btnAgregarPlato)
        toolbar =findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener{
            val pantallaGestPlato = Intent(this, GestPlatosActivity::class.java)
            startActivity(pantallaGestPlato)
            finish()
        }

        platoDao = PlatoDao(this)

        btnAgregarPlato.setOnClickListener {
            registrarPlato()
        }

    }
    fun registrarPlato(){
        val codplato = txtCodPlato.text.toString()
        val descripcion = txtDescripcion.text.toString()
        val costouni = txtCostoUni.text.toString()
        val costofami = txtCostoFami.text.toString()

        if (codplato.isEmpty() || descripcion.isEmpty() || costouni.isEmpty() || costofami.isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        val costouniD = costouni.toDoubleOrNull() ?: 0.0
        val costofamiD = costofami.toDoubleOrNull() ?: 0.0
        platoDao.agregarPlato(codplato, descripcion, costouniD, costofamiD) {exito ->
            if (exito){
                Toast.makeText(this, "Plato registrado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            } else{
                Toast.makeText(this, "Error al registrar Plato", Toast.LENGTH_SHORT).show()
            }
        }
    }
}