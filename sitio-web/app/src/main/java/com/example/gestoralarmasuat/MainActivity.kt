package com.example.gestoralarmasuat

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var estadoCrud = "SIN REGISTROS ACTIVOS"
    private var alarmaMemoria = "Ninguna"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuarioActivo = intent.getStringExtra("USUARIO_ACTIVO") ?: "admin@uat.edu.mx"
        val tvMensajeAlarmas = findViewById<TextView>(R.id.tvMensajeAlarmas)
        val btnCrear = findViewById<Button>(R.id.btnCrear)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnGenerarReporte = findViewById<Button>(R.id.btnGenerarReporte)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Abre el TimePickerDialog al presionar CREAR
        btnCrear.setOnClickListener {
            val calendario = Calendar.getInstance()
            val horaActual = calendario.get(Calendar.HOUR_OF_DAY)
            val minutoActual = calendario.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this,
                { _, horaSeleccionada, minutoSeleccionado ->
                    val horaFormateada = String.format(Locale.getDefault(), "%02d:%02d", horaSeleccionada, minutoSeleccionado)

                    estadoCrud = "REGISTROS ACTIVOS"
                    alarmaMemoria = "$horaFormateada"
                    tvMensajeAlarmas.text = "Alarma Programada: $horaFormateada"

                    Toast.makeText(this, "Notificación: Alarma programada a las $horaFormateada", Toast.LENGTH_LONG).show()
                }, horaActual, minutoActual, true) // Formato de 24 horas activo

            timePickerDialog.show()
        }

        btnEliminar.setOnClickListener {
            estadoCrud = "SIN REGISTROS ACTIVOS"
            alarmaMemoria = "Ninguna"
            tvMensajeAlarmas.text = "No tienes alarmas programadas"
            Toast.makeText(this, "Alarma eliminada del sistema", Toast.LENGTH_SHORT).show()
        }

        btnGenerarReporte.setOnClickListener {
            val intent = Intent(this, ReporteActivity::class.java).apply {
                putExtra("USUARIO", usuarioActivo)
                putExtra("CRUD", estadoCrud)
                putExtra("ALARMA", alarmaMemoria)
            }
            startActivity(intent)
        }

        btnCerrarSesion.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
