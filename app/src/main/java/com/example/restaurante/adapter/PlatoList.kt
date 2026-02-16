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
import com.example.restaurante.dao.PlatoDao
import com.example.restaurante.models.Plato
import com.example.restaurante.ui.platos.EditarPlatoActivity

class PlatoList (private val context: Activity, internal var platos: List<Plato>):
        ArrayAdapter<Plato>(context, R.layout.layout_list_plato, platos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_plato, parent, false)

        val txtCod = listViewItem.findViewById<TextView>(R.id.textViewCod)
        val txtDesc = listViewItem.findViewById<TextView>(R.id.textViewDescrip)
        val txtPreU = listViewItem.findViewById<TextView>(R.id.textViewPrecioU)
        val txtPreF = listViewItem.findViewById<TextView>(R.id.textViewPrecioF)
        val btnEliminarPlato = listViewItem.findViewById<ImageButton>(R.id.btnEliminarPlato)
        val btnEditarPlato = listViewItem.findViewById<ImageButton>(R.id.btnEditarPlato)

        val plato = platos[position]
        txtCod.text = "COD: ${plato.idplato}"
        txtDesc.text = plato.descripcion
        txtPreU.text = "Uni: S/ ${plato.costounitario}"
        txtPreF.text = "Fam: S/ ${plato.costofamiliar}"


        btnEliminarPlato.setOnClickListener {
          val dao = PlatoDao(context)
            dao.eliminarPlato(plato.idplato) { exito ->
                if (exito) {
                    Toast.makeText(context, "Plato eliminado", Toast.LENGTH_SHORT).show()
                    context.recreate() // Refresca la lista actual
                } else {
                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnEditarPlato.setOnClickListener {
            val intent = Intent(context, EditarPlatoActivity::class.java)
            intent.putExtra("idplato", plato.idplato)
            intent.putExtra("descripcion", plato.descripcion)
            intent.putExtra("costounitario", plato.costounitario)
            intent.putExtra("costofamiliar", plato.costofamiliar)
            context.startActivity(intent)
        }

        return listViewItem
    }
}