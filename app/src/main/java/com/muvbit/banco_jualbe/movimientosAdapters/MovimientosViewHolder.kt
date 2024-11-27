package com.muvbit.banco_jualbe.movimientosAdapters

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.databinding.ItemMovimientosBinding
import com.muvbit.banco_jualbe.pojo.Movimiento

class MovimientosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        }

    }

}