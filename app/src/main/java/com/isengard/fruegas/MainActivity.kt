package com.isengard.fruegas

import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Registrar la creación de la pantalla
        Log.d("FraguasIsengard", "onCreate: Saruman abre las fraguas")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Obtengo los controles del formulario
        val identificador = findViewById<EditText>(R.id.editTextIdentificador)
        val tipoUnidad = findViewById<Spinner>(R.id.spinnerTipoUnidad)
        val equipamiento = findViewById<RadioGroup>(R.id.radioGroupEquipamiento)
        val antorcha = findViewById<CheckBox>(R.id.checkBoxAntorcha)
        val enviar = findViewById<ImageButton>(R.id.imageButtonEnviar)

        //Le doy el foco al identificador principal
        identificador.requestFocus()

        //Aquí voy a hacer que si quito el foco del identificador principal
        //aparezca un error
        identificador.setOnFocusChangeListener { _, tieneFoco ->

            if (!tieneFoco) {
                if (identificador.text.toString().trim().isEmpty()) {
                    identificador.error = "El ejército no acepta soldados anónimos"
                } else {
                    identificador.error = null
                }
            }
        }

        enviar.setOnClickListener {
            val idTropa = identificador.text.toString().trim()
            if (idTropa.isEmpty()) {
                identificador.error = "El ejército no acepta soldados anónimos"
            } else {
                identificador.error = null

                val unidad = tipoUnidad.selectedItem.toString()

                val equipo = when (equipamiento.checkedRadioButtonId) {
                    R.id.radioButtonArmadura -> "Armadura de hierro"
                    R.id.radioButtonEscudo -> "Escudo de Isengard"
                    else -> "Sin equipamiento"
                }

                val llevaAntorcha = antorcha.isChecked

                Log.d("FraguasIsengard", "ID: $idTropa, unidad: $unidad, equipo: $equipo, antorcha: $llevaAntorcha")

                if (!llevaAntorcha) {
                    Log.e("FraguasIsengard", "¡Peligro! Unidad enviada sin fuego")
                }

                Toast.makeText(this, "¡Unidad $idTropa enviada al Abismo de Helm!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("FraguasIsengard", "onStart: Las fraguas se encienden")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FraguasIsengard", "onResume: Comienza la producción de Uruk-hai")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FraguasIsengard", "onPause: Saruman detiene la producción temporalmente")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FraguasIsengard", "onStop: Las fraguas quedan en reposo")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FraguasIsengard", "onDestroy: Se cierran las fraguas")
    }
}