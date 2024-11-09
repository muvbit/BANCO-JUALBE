package com.muvbit.banco_jualbe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muvbit.banco_jualbe.bd.MiBancoOperacional
import com.muvbit.banco_jualbe.databinding.MainBinding
import com.muvbit.banco_jualbe.pojo.Cliente

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ací agafem el usuari que anteriorment havem passat des de LoginActivity
        val cliente = intent.getSerializableExtra("cliente") as Cliente
        if (cliente != null) {
            binding.bienvenida.text = getString(R.string.bienvenida) + " " + cliente.getNombre()
        }

        val botonConfiguracion = binding.cardViewConfiguracion
        val btnTransferencias = binding.cardViewTransferencias

        btnTransferencias.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        botonConfiguracion.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            intent.putExtra("cliente",cliente)
            startActivity(intent)
        }
    }
}
