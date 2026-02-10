package com.example.restaurante.api

object EndPoints {
    private const val BASE_URL = "http://192.168.18.13:8080/api"

    object Usuarios {
        const val ROOT = "$BASE_URL/usuarios"
        const val LOGIN = "$ROOT/login"
    }
}