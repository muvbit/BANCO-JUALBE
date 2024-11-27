package com.muvbit.banco_jualbe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvbit.banco_jualbe.cuentasAdapters.CuentasAdapter
import com.muvbit.banco_jualbe.dao.CuentaDAO
import com.muvbit.banco_jualbe.databinding.CuentasBinding
import com.muvbit.banco_jualbe.pojo.Cliente
import com.muvbit.banco_jualbe.pojo.Cuenta

class CuentasActivity : AppCompatActivity() {
    lateinit var binding: CuentasBinding
    lateinit var cliente: Cliente
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = CuentasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cliente = intent.getSerializableExtra("cliente") as Cliente
        initRecyclerView()
    }

    fun initRecyclerView() {
        binding.run {
            cuentasRV.layoutManager = LinearLayoutManager(this@CuentasActivity)
            cuentasRV.adapter = CuentasAdapter(CuentaDAO().getCuentas(cliente) as ArrayList<Cuenta>)
        }
    }
}
