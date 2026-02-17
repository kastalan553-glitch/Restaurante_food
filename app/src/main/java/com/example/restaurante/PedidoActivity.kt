package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.example.restaurante.dao.MozoDao
import com.example.restaurante.dao.PedidoDao
import com.example.restaurante.dao.PlatoDao
import com.example.restaurante.models.Mozo
import com.example.restaurante.models.Pedido
import com.example.restaurante.models.Plato
import java.text.SimpleDateFormat
import java.util.*

class PedidoActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar

    lateinit var cbMozo: Spinner
    lateinit var cbPlato: Spinner
    lateinit var cbMesa: Spinner
    lateinit var txtHora: EditText
    lateinit var txtIdPedido: EditText
    lateinit var txtCantidad: EditText
    lateinit var txtObservacion: EditText
    lateinit var rbUnitario: RadioButton
    lateinit var rbFamiliar: RadioButton
    lateinit var btnRegistrar: Button

    lateinit var pedidoDao: PedidoDao

    var listaMozos: List<Mozo> = listOf()
    var listaPlatos: List<Plato> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        txtIdPedido = findViewById(R.id.txtPedido)
        txtHora = findViewById(R.id.txtHora)
        txtCantidad = findViewById(R.id.txtCantidad)
        txtObservacion = findViewById(R.id.txtObservación)
        cbMesa = findViewById(R.id.cbMesa)
        cbMozo = findViewById(R.id.cbMozo)
        cbPlato = findViewById(R.id.cbPlato)
        rbUnitario = findViewById(R.id.rbUnitario)
        rbFamiliar = findViewById(R.id.rbFamilia)
        btnRegistrar = findViewById(R.id.btnAgregarPedido)
        toolbar = findViewById(R.id.toolbar)

        txtHora.setText(
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        )
        pedidoDao = PedidoDao(this)

        cargarPlatos()
        cargarMozos()

        btnRegistrar.setOnClickListener {
            registrarPedido()
        }

        toolbar.setNavigationOnClickListener{
            val pantallaHome = Intent(this, HomeActivity::class.java)
            startActivity(pantallaHome)
            finish()
        }
    }


    fun cargarPlatos() {
        val platoDao = PlatoDao(this)
        platoDao.listarPlatos { platos ->
            if (platos != null) {
                // 1. Guardamos la lista original
                this.listaPlatos = platos

                // 2. Extraemos descripciones asegurándonos de que no haya nulos
                // Usamos "mapNotNull" para omitir platos si por error falta la descripción
                val descripciones = platos.mapNotNull { it.descripcion }

                // 3. Verificamos que la lista de descripciones no esté vacía
                if (descripciones.isNotEmpty()) {
                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        descripciones
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    cbPlato.adapter = adapter
                } else {
                    Toast.makeText(this, "No hay descripciones de platos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Error: No se recibió respuesta del servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun cargarMozos() {
        val mozoDao = MozoDao(this)
        mozoDao.listarMozos { mozos ->
            if (mozos != null) {
                // Guardamos la lista completa para el registro final
                this.listaMozos = mozos

                // Creamos la lista de nombres para el Spinner
                val nombres = mozos.map { it.nombre }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    nombres
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                cbMozo.adapter = adapter
            }
        }
    }


    fun registrarPedido() {
        val idPedido = txtIdPedido.text.toString()
        val cantidadStr = txtCantidad.text.toString()
        val detalleObs = txtObservacion.text.toString()
        val hora = txtHora.text.toString()

        if (idPedido.isEmpty() || cantidadStr.isEmpty() || listaPlatos.isEmpty() || listaMozos.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos y espere la carga de datos", Toast.LENGTH_SHORT).show()
            return
        }


        val platoSeleccionado = listaPlatos[cbPlato.selectedItemPosition]
        val mozoSeleccionado = listaMozos[cbMozo.selectedItemPosition]
        val mesaSeleccionada = cbMesa.selectedItem.toString()

        val tipo = if (rbUnitario.isChecked) "Unitario" else "Familiar"


        val cantidadInt = cantidadStr.toIntOrNull() ?: 0

        val nuevoPedido = Pedido(
            idpedido = idPedido,
            nmesa = mesaSeleccionada,
            hora = hora,
            cantidad = cantidadInt,
            observacion = detalleObs,
            tipoPlato = tipo,
            plato = platoSeleccionado,
            mozo = mozoSeleccionado
        )

        pedidoDao.registrarPedido(nuevoPedido) { exito ->
            if (exito) {
                Toast.makeText(this, "Pedido registrado con éxito", Toast.LENGTH_LONG).show()
                finish() // Cierra la pantalla y vuelve al Home
            } else {
                Toast.makeText(this, "Error al registrar el pedido en el servidor", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

