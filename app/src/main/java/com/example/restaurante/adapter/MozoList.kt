package com.example.restaurante.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.restaurante.R
import com.example.restaurante.dao.MozoDao
import com.example.restaurante.models.Mozo

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

        val mozo = mozos[position]

        txtDni.text = "DNI: ${mozo.dnimozo}"
        txtNombre.text = mozo.nombre
        txtDireccion.text = "Dirección: ${mozo.direccion}"
        txtMovil.text = "Cel: ${mozo.movil}"
        txtEmail.text = mozo.email
        txtFecha.text = "Ingreso: ${mozo.fechaingreso}"

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