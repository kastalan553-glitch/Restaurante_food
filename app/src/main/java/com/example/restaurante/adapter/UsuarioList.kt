package com.example.restaurante.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.restaurante.R
import com.example.restaurante.dao.UsuarioDao
import com.example.restaurante.models.Usuario

class UsuarioList(private val context: Activity, internal var users: List<Usuario>) :
    ArrayAdapter<Usuario>(context, R.layout.layout_list_usuario, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_usuario, parent, false)

        val textViewId = listViewItem.findViewById(R.id.textViewId) as TextView
        val textViewName = listViewItem.findViewById(R.id.textViewNombre) as TextView
        val btnEliminarUsuario = listViewItem.findViewById<ImageButton>(R.id.btnEliminarUsuario)

        val user = users[position]

        textViewId.text = "ID: ${user.id}"
        textViewName.text = user.nombre


        btnEliminarUsuario.setOnClickListener {
            val dao = UsuarioDao(context)
            dao.eliminarUsuario(user.id) { exito ->
                if (exito) {
                    Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show()
                    (context as Activity).recreate()
                } else {
                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return listViewItem
    }
}