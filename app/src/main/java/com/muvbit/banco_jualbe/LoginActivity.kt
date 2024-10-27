package com.muvbit.banco_jualbe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.muvbit.banco_jualbe.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrar.setOnClickListener {
            val usuario = binding.user.editText?.text.toString()
            val contraseña = binding.password.editText?.text.toString()
            if (usuario.isEmpty()) {
                showSnackbar("El usuario no puede estar vacío.")
                return@setOnClickListener
            }
            if (contraseña.isEmpty()) {
                showSnackbar("La contraseña no puede estar vacía.")
                return@setOnClickListener
            }

            // OJO!! Ací passem el usuari al LoginActivity amb la següent funció:
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("usuario", usuario)
            intent.putExtra("contraseña", contraseña)
            startActivity(intent)
            finish()
        }

        binding.salir.setOnClickListener {
            finish()
        }
    }

    private fun showSnackbar(mensaje: String) {
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG).show()
    }
}
