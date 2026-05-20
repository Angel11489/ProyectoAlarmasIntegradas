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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnMostrarRegistro = findViewById<Button>(R.id.btnMostrarRegistro)
        val layoutRegistro = findViewById<LinearLayout>(R.id.layoutRegistro)
        val etNuevoCorreo = findViewById<EditText>(R.id.etNuevoCorreo)
        val btnFinalizarRegistro = findViewById<Button>(R.id.btnFinalizarRegistro)

        btnIngresar.setOnClickListener {
            val correo = etCorreo.text.toString()
            if (correo.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USUARIO_ACTIVO", correo)
                startActivity(intent)
                finish()
            }
        }

        // Alternar el despliegue del registro en la misma pestaña
        btnMostrarRegistro.setOnClickListener {
            layoutRegistro.visibility = if (layoutRegistro.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        btnFinalizarRegistro.setOnClickListener {
            val nuevoCorr = etNuevoCorreo.text.toString()
            if (nuevoCorr.isNotEmpty()) {
                etCorreo.setText(nuevoCorr)
                layoutRegistro.visibility = View.GONE
                Toast.makeText(this, "Registrado. Datos cargados arriba.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
