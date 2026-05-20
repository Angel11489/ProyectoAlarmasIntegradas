package com.example.gestoralarmasuat

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReporteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte)

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        val tvConsolaReporte = findViewById<TextView>(R.id.tvConsolaReporte)

        val user = intent.getStringExtra("USUARIO") ?: "osiris@uat.edu.mx"
        val status = intent.getStringExtra("CRUD") ?: "SIN REGISTROS ACTIVOS"
        val alarm = intent.getStringExtra("ALARMA") ?: "Ninguna"

        val plantillaTerminal = """
            =========================
            
            Usuario Activo: $user
            Estado del CRUD: $status
            Alarma en memoria: $alarm
            
            Sincronización de Base de Datos: COMPLETA
            Estado de la App: OPERATIVA
        """.trimIndent()

        tvConsolaReporte.text = plantillaTerminal
        btnVolver.setOnClickListener { finish() }
    }
}
