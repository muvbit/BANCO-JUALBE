package com.muvbit.banco_jualbe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muvbit.banco_jualbe.databinding.MainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ací agafem el usuari que anteriorment havem passat des de LoginActivity
        val usuario = intent.getStringExtra("usuario")
        if (usuario != null) {
            binding.bienvenida.text = "Bienvenido/a $usuario"
        }
    }
}
