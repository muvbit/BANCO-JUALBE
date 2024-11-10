package com.muvbit.banco_jualbe.cuentasAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.pojo.Cuenta

class CuentasAdapter(private val cuentasList: ArrayList<Cuenta>) :
    RecyclerView.Adapter<CuentasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CuentasViewHolder(layoutInflater.inflate(R.layout.item_cuentas, parent, false))
    }

    override fun getItemCount(): Int = cuentasList.size


    override fun onBindViewHolder(holder: CuentasViewHolder, position: Int) {
        val item = cuentasList[position]
        holder.render(item)
    }
}