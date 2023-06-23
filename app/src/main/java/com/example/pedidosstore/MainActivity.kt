package com.example.pedidosstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewRegister: TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Obtener referencias de los elementos del layout
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        textViewRegister = findViewById(R.id.textViewRegister)

        // Asociar evento click al botón de inicio de sesión
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Validar campos vacíos
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Iniciar sesión con Firebase
                signIn(email, password)
            } else {
                // Mostrar mensaje de error
                Toast.makeText(this, "Ingresa datos Validos, Por favor", Toast.LENGTH_SHORT).show()
            }
        }

        // Asociar evento click al texto de registro
        textViewRegister.setOnClickListener {
            // Abrir actividad de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso, redirigir a la actividad principal
                    Toast.makeText(this, "Bienvenido.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Error en el inicio de sesión, mostrar mensaje de error
                    Toast.makeText(this, "Autentificacion Fallida.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}