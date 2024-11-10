package com.muvbit.banco_jualbe.movimientosAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.cuentasAdapters.CuentasAdapter
import com.muvbit.banco_jualbe.cuentasAdapters.CuentasViewHolder
import com.muvbit.banco_jualbe.pojo.Movimiento

class MovimientosAdapter (private val movimientos : ArrayList<Movimiento>):RecyclerView.Adapter<MovimientosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientosViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return MovimientosViewHolder(layoutInflater.inflate(R.layout.item_movimientos,parent,false))
    }

    override fun getItemCount(): Int = movimientos.size


    override fun onBindViewHolder(holder: MovimientosViewHolder, position: Int) {
       val item =movimientos[position]
        holder.render(item)
    }

}