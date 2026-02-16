package com.example.restaurante.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.restaurante.HomeActivity
import com.example.restaurante.R
import com.example.restaurante.dao.LoginDao
import com.example.restaurante.models.Login
import com.example.restaurante.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    lateinit var txtUsuario: EditText
    lateinit var txtClave: EditText
    lateinit var btnLogin: Button
    lateinit var btnCerrar: Button
    lateinit var loginDao: LoginDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        txtUsuario = findViewById(R.id.txtUsuario)
        txtClave = findViewById(R.id.txtClave)
        btnLogin = findViewById(R.id.btnLogin)
        btnCerrar = findViewById(R.id.btnCerrarLogin)

        loginDao = LoginDao(this)

        btnLogin.setOnClickListener {
            VerificarCredenciales()
        }
        btnCerrar.setOnClickListener {
            llamarMain()
        }
    }

    private fun VerificarCredenciales() {
        val usuarioInput = txtUsuario.text.toString().trim()
        val claveInput = txtClave.text.toString().trim()


        if (usuarioInput.isEmpty() || claveInput.isEmpty()) {
            Toast.makeText(this, "Ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Objeto para enviar al dao xd
        val misCredenciales = Login(usuarioInput, claveInput)

        loginDao.login(misCredenciales) {
                loginCorrecto -> if (loginCorrecto) {
                    Toast.makeText(this, "¡Bienvenido a Don Aurelio!", Toast.LENGTH_SHORT).show()
                    llamarHome()
                } else {
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun llamarHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun llamarMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}