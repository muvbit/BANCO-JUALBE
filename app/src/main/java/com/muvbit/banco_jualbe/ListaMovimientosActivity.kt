package com.muvbit.banco_jualbe

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvbit.banco_jualbe.dao.CuentaDAO
import com.muvbit.banco_jualbe.dao.MovimientoDAO
import com.muvbit.banco_jualbe.databinding.ListaMovimientosBinding
import com.muvbit.banco_jualbe.movimientosAdapters.MovimientosAdapter
import com.muvbit.banco_jualbe.pojo.Cliente
import com.muvbit.banco_jualbe.pojo.Cuenta
import com.muvbit.banco_jualbe.pojo.Movimiento

class ListaMovimientosActivity : AppCompatActivity() {
    lateinit var binding: ListaMovimientosBinding
    lateinit var cliente: Cliente
    lateinit var cuentaSeleccionada: Cuenta
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ListaMovimientosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        cliente = intent.getSerializableExtra("cliente") as Cliente
        val cuentas = CuentaDAO().getCuentas(cliente) as ArrayList<Cuenta> //RECOGEMOS LA LISTA DE CUENTAS DEL CLIENTE
        val numerosCuenta = cuentas.map { it.getCuentaCompleta()?:"No existen cuentas" } //HACEMOS UN MAPEO PARA RECOGER LOS NUMEROS DE LAS CUENTAS
        val adapterCuentas = ArrayAdapter(this, android.R.layout.simple_spinner_item, numerosCuenta) // CREAMOS EL ADAPTADOR PARA PASAR EL ARRAY AL SPINNER
        binding.run {
            adapterCuentas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnCuenta.adapter = adapterCuentas
            spnCuenta.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    initRecyclerView(cuentas[p2])
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            })

        }

    }

    fun initRecyclerView(cuenta: Cuenta) {
        binding.run {
            movimientosRV.layoutManager = LinearLayoutManager(this@ListaMovimientosActivity)
            movimientosRV.addItemDecoration(
                DividerItemDecoration(
                    this@ListaMovimientosActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            movimientosRV.adapter =
                MovimientosAdapter(MovimientoDAO().getMovimientos(cuenta) as ArrayList<Movimiento>)
        }
    }
}



