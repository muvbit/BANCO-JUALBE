package com.muvbit.banco_jualbe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muvbit.banco_jualbe.dao.CuentaDAO
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
        binding.run {

            cardViewTransferencias.setOnClickListener {
                val intent = Intent(this@MainActivity, TransferActivity::class.java)
                intent.putExtra("cliente",cliente)
                startActivity(intent)
            }

            cardViewConfiguracion.setOnClickListener {
                val intent = Intent(this@MainActivity, ConfigurationActivity::class.java)
                intent.putExtra("cliente", cliente)
                startActivity(intent)
            }

            cardViewCuentas.setOnClickListener {
                val intent = Intent(this@MainActivity, GlobalPositionActivity::class.java)
                intent.putExtra("cliente", cliente)
                startActivity(intent)
            }
            cardViewOperaciones.setOnClickListener {
                val intent=Intent(this@MainActivity,OperacionesActivity::class.java)
                intent.putExtra("cliente",cliente)
                startActivity(intent)
            }
            saldoActual.text = "********* €"

            swSaldo.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    saldoActual.text = "********* €"
                } else saldoActual.text =
                    CuentaDAO().getSaldoCuentasCliente(cliente).toString() + " €"
            }
            imgLogout.setOnClickListener {
                val intent=Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
}

