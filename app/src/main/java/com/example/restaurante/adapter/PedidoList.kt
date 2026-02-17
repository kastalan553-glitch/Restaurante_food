package com.example.restaurante.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.restaurante.R
import com.example.restaurante.models.Pedido

class PedidoList (private val context: Activity, private var pedidos: List<Pedido>) :
    ArrayAdapter<Pedido>(context, R.layout.layout_list_pedido, pedidos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_pedido, null, true)

        val txtCod = listViewItem.findViewById<TextView>(R.id.textViewCod)
        val txtPlato = listViewItem.findViewById<TextView>(R.id.textViewPlato)
        val txtMesa = listViewItem.findViewById<TextView>(R.id.textViewDescrip)
        val txtHora = listViewItem.findViewById<TextView>(R.id.textViewPrecioU)
        val txtCant = listViewItem.findViewById<TextView>(R.id.textViewCantidad)
        val txtTipo = listViewItem.findViewById<TextView>(R.id.textViewTipoP)
        val txtObs = listViewItem.findViewById<TextView>(R.id.textViewObsev)
        val txtMozo = listViewItem.findViewById<TextView>(R.id.textViewMozo)

        val pedido = pedidos[position]

        txtCod.text = "ID: ${pedido.idpedido}"
        txtPlato.text = "Plato: ${pedido.plato.descripcion}" // Accedemos al objeto anidado
        txtMesa.text = "Mesa: ${pedido.nmesa}"
        txtHora.text = "Hora: ${pedido.hora}"
        txtCant.text = "Cantidad: ${pedido.cantidad}"
        txtTipo.text = "Tipo: ${pedido.tipoPlato}"
        txtObs.text = "Obs: ${pedido.observacion}"
        txtMozo.text = "Mozo: ${pedido.mozo.nombre}"

        return listViewItem
    }
}