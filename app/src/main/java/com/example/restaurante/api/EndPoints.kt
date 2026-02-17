package com.example.restaurante.api

object EndPoints {
    const val BASE_URL = "http://192.168.18.13:8080/api"

    object Usuarios {
        const val ROOT = "$BASE_URL/usuarios"
        const val LOGIN = "$ROOT/login"
        const val ADD = "$ROOT/agregar"
        const val LISTAR = "$ROOT/listar"
        const val DELETE = "$ROOT/eliminar"
        const val UPDATE = "$ROOT/actualizar"
    }
    object Platos {
        const val ROOT = "$BASE_URL/platos"
        const val ADD = "$ROOT/agregar"
        const val DELETE = "$ROOT/eliminar"
        const val LISTAR = "$ROOT/listar"
        const val UPDATE = "${ROOT}/actualizar"
    }
    object Mozos {
        const val ROOT = "$BASE_URL/mozos"
        const val ADD = "${ROOT}/agregar"
        const val LISTAR = "${ROOT}/listar"
        const val DELETE = "${ROOT}/eliminar"
        const val UPDATE = "${ROOT}/actualizar"
    }
    object Pedidos {
        const val ROOT = "$BASE_URL/pedido"
        const val ADD = "${ROOT}/agregar"
        const val LISTAR = "${ROOT}/listar"
    }
}