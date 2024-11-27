package com.muvbit.banco_jualbe.cuentasAdapters

import android.content.Intent
import android.provider.DocumentsContract.Root
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.MovimientosActivity
import com.muvbit.banco_jualbe.databinding.ItemCuentasBinding
import com.muvbit.banco_jualbe.pojo.Cuenta

class CuentasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemCuentasBinding.bind(view)

    fun render(cuenta: Cuenta) {
        binding.run {
            tvCuenta.text = cuenta.getCuentaCompleta()
            tvSaldo.text = cuenta.getSaldoActual().toString() + " €"
            if (cuenta.getSaldoActual()!! < 0) tvSaldo.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    android.R.color.holo_red_dark
                )
            )
            cvMain.setOnClickListener {
                val intent = Intent(cvMain.context, MovimientosActivity::class.java)
                intent.putExtra("cuenta", cuenta)
                ContextCompat.startActivity(cvMain.context,intent,null)
            }
        }
    }
}
