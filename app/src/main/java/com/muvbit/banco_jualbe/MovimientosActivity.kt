package com.muvbit.banco_jualbe

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvbit.banco_jualbe.dao.MovimientoDAO
import com.muvbit.banco_jualbe.databinding.CuentasBinding
import com.muvbit.banco_jualbe.databinding.MovimientosBinding
import com.muvbit.banco_jualbe.movimientosAdapters.MovimientosAdapter
import com.muvbit.banco_jualbe.pojo.Cuenta
import com.muvbit.banco_jualbe.pojo.Movimiento

class MovimientosActivity : AppCompatActivity() {
    lateinit var binding: MovimientosBinding
    lateinit var cuenta: Cuenta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cuenta=intent.getSerializableExtra("cuenta") as Cuenta
        binding.tvCuenta.text=cuenta.getCuentaCompleta().toString()
        initRecyclerView()
    }

    fun initRecyclerView() {
        binding.run{
            movimientosRV.layoutManager=LinearLayoutManager(this@MovimientosActivity)
            movimientosRV.addItemDecoration(DividerItemDecoration(this@MovimientosActivity,LinearLayoutManager.VERTICAL))
            movimientosRV.adapter=MovimientosAdapter(MovimientoDAO().getMovimientos(cuenta) as ArrayList<Movimiento>)
        }
    }
}