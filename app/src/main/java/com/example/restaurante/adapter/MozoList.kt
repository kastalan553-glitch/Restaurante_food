package com.example.restaurante.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.restaurante.R
import com.example.restaurante.dao.MozoDao
import com.example.restaurante.models.Mozo
import com.example.restaurante.ui.mozos.EditarMozoActivity

class MozoList(
    private val context: Activity,
    internal var mozos: List<Mozo>
) : ArrayAdapter<Mozo>(context, R.layout.layout_list_mozo, mozos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listViewItem = convertView ?: context.layoutInflater
            .inflate(R.layout.layout_list_mozo, parent, false)

        val txtDni = listViewItem.findViewById<TextView>(R.id.textViewDni)
        val txtNombre = listViewItem.findViewById<TextView>(R.id.textViewNombre)
        val txtDireccion = listViewItem.findViewById<TextView>(R.id.textViewDireccion)
        val txtMovil = listViewItem.findViewById<TextView>(R.id.textViewMovil)
        val txtEmail = listViewItem.findViewById<TextView>(R.id.textViewEmail)
        val txtFecha = listViewItem.findViewById<TextView>(R.id.textViewFechaIngreso)
        val btnEliminar = listViewItem.findViewById<ImageButton>(R.id.btnEliminarMozo)
        val btnEditar = listViewItem.findViewById<ImageButton>(R.id.btnEditarMozo)

        val mozo = mozos[position]

        txtDni.text = "DNI: ${mozo.dnimozo}"
        txtNombre.text = mozo.nombre
        txtDireccion.text = "Dirección: ${mozo.direccion}"
        txtMovil.text = "Cel: ${mozo.movil}"
        txtEmail.text = mozo.email
        txtFecha.text = "Ingreso: ${mozo.fechaingreso}"

        btnEditar.setOnClickListener {
            val intent = Intent(context, EditarMozoActivity::class.java)
            intent.putExtra("dnimozo", mozo.dnimozo)
            intent.putExtra("nombre", mozo.nombre)
            intent.putExtra("direccion", mozo.direccion)
            intent.putExtra("fechaingreso", mozo.fechaingreso)
            intent.putExtra("movil", mozo.movil)
            intent.putExtra("email", mozo.email)
            context.startActivity(intent)
        }

        btnEliminar.setOnClickListener {
            val dao = MozoDao(context)
            dao.eliminarMozo(mozo.dnimozo) { exito ->
                if (exito) {
                    Toast.makeText(context, "Mozo eliminado", Toast.LENGTH_SHORT).show()
                    context.recreate()
                } else {
                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return listViewItem
    }
}