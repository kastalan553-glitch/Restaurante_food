package com.example.restaurante.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.restaurante.R
import com.example.restaurante.dao.UsuarioDao
import com.example.restaurante.models.Usuario
import com.example.restaurante.ui.usuarios.EditarUsuarioActivity

class UsuarioList(private val context: Activity, internal var users: List<Usuario>) :
    ArrayAdapter<Usuario>(context, R.layout.layout_list_usuario, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_usuario, parent, false)

        val textViewId = listViewItem.findViewById<TextView>(R.id.textViewId)
        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewNombre)
        val btnEliminarUsuario = listViewItem.findViewById<ImageButton>(R.id.btnEliminarUsuario)
        val btnEditar = listViewItem.findViewById<ImageButton>(R.id.btnEditarUsuario)

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

        btnEditar.setOnClickListener {
            val intent = Intent(context, EditarUsuarioActivity::class.java)
            intent.putExtra("id", user.id)
            intent.putExtra("nombre", user.nombre)
            intent.putExtra("clave", user.clave)
            context.startActivity(intent)
        }

        return listViewItem
    }
}