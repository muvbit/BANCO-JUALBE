package com.muvbit.banco_jualbe.globalPositionAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.databinding.ItemMovimientosBinding
import com.muvbit.banco_jualbe.pojo.Movimiento

class MovementsAdapter (private val movimientos : ArrayList<Movimiento>):
        RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movimientos, parent, false))
    }

    override fun getItemCount(): Int = movimientos.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movimientos[position]
        holder.render(item)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovimientosBinding.bind(view)

        fun render(movimiento: Movimiento) {
            binding.run {
                tvDescripcion.text = movimiento.getDescripcion()
                tvImporte.text = movimiento.getImporte().toString()
                fecha.text = movimiento.getFechaOperacion().toString()
                if (movimiento.getImporte() < 0) {
                    tvImporte.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            android.R.color.holo_red_dark
                        )
                    )
                    imgImagen.setImageDrawable(
                        ContextCompat.getDrawable(
                            this.root.context,
                            R.drawable.flecha_gasto
                        )
                    )
                } else {
                    imgImagen.setImageDrawable(
                        ContextCompat.getDrawable(
                            this.root.context,
                            R.drawable.flecha_ingreso
                        )
                    )
                    tvImporte.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            android.R.color.holo_green_light
                        )
                    )
                }
                cvMain.setOnClickListener {
                    //IMPLEMENTAR EL CLICK EN UN ALERTDIALOG QUE MOSTRE DETALLS
                }
            }

        }
    }
}

