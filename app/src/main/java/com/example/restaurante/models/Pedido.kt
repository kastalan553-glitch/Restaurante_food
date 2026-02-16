package com.example.restaurante.models

class Pedido(val idpedido: String, val nmesa: String, val hora: String, val cantidad: Int,
    val observacion: String,val tipoPlato: String, val plato: Plato, val mozo: Mozo
)