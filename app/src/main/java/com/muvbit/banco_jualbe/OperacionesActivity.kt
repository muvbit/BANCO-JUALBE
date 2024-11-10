package com.muvbit.banco_jualbe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muvbit.banco_jualbe.databinding.OperacionesBinding
import com.muvbit.banco_jualbe.pojo.Cliente

class OperacionesActivity : AppCompatActivity() {
    lateinit var binding: OperacionesBinding
    lateinit var cliente: Cliente
    override fun onCreate(savedInstanceState: Bundle?) {
        cliente = intent.getSerializableExtra("cliente") as Cliente
        binding = OperacionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {
            cvMovimientos.setOnClickListener {
                val intent = Intent(this@OperacionesActivity, ListaMovimientosActivity::class.java)
                intent.putExtra("cliente", cliente)
                startActivity(intent)
            }
        }
    }
}
