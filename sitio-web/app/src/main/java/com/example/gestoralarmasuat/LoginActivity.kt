package com.example.gestoralarmasuat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // 🔐 Base de datos local simulada para controlar accesos
    private val usuariosValidos = hashMapOf(
        "admin" to "12345",
        "osiris" to "1234"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Enlaces del login normal
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // Enlaces del sistema de registro desplegable
        val btnMostrarRegistro = findViewById<Button>(R.id.btnMostrarRegistro)
        val layoutRegistro = findViewById<LinearLayout>(R.id.layoutRegistro)
        val etNuevoCorreo = findViewById<EditText>(R.id.etNuevoCorreo)
        val etNuevaContrasena = findViewById<EditText>(R.id.etNuevaContrasena)
        val btnFinalizarRegistro = findViewById<Button>(R.id.btnFinalizarRegistro)

        // 1. Mostrar / Ocultar el formulario de registro
        btnMostrarRegistro.setOnClickListener {
            layoutRegistro.visibility = if (layoutRegistro.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        // 2. Procesar y registrar la nueva cuenta en memoria
        btnFinalizarRegistro.setOnClickListener {
            val correoRegistrado = etNuevoCorreo.text.toString().trim()
            val contraRegistrada = etNuevaContrasena.text.toString().trim()

            if (correoRegistrado.isNotEmpty() && contraRegistrada.isNotEmpty()) {
                // Guardar en nuestra base de datos local
                usuariosValidos[correoRegistrado] = contraRegistrada

                // Mover los datos arriba para agilizar el ingreso
                etCorreo.setText(correoRegistrado)
                etContrasena.setText(contraRegistrada)

                // Ocultar sección y limpiar campos internos
                layoutRegistro.visibility = View.GONE
                etNuevoCorreo.text.clear()
                etNuevaContrasena.text.clear()

                Toast.makeText(this, "Cuenta registrada. Presiona INGRESAR", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Por favor llena todos los campos de registro", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Validación estricta de credenciales al ingresar
        btnIngresar.setOnClickListener {
            val correoIngresado = etCorreo.text.toString().trim()
            val contrasenaIngresada = etContrasena.text.toString().trim()

            // Verifica si el correo existe en la base de datos
            if (usuariosValidos.containsKey(correoIngresado)) {
                // Verifica si la contraseña coincide con ese correo
                val contrasenaCorrecta = usuariosValidos[correoIngresado]

                if (contrasenaIngresada == contrasenaCorrecta) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USUARIO_ACTIVO", correoIngresado)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El usuario no está registrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}