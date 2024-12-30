package com.muvbit.banco_jualbe.globalPositionAdapters

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muvbit.banco_jualbe.GlobalPositionActivity
import com.muvbit.banco_jualbe.GlobalPositionDetailsActivity
import com.muvbit.banco_jualbe.MovimientosActivity
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.databinding.ActivityGlobalPositionBinding
import com.muvbit.banco_jualbe.databinding.ItemCuentasBinding
import com.muvbit.banco_jualbe.fragments.AccountsMovementFragment
import com.muvbit.banco_jualbe.pojo.Cuenta

class AccountsAdapter (private var cuentasList: ArrayList<Cuenta>):RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_cuentas, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cuentasList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = cuentasList.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

                    val activity = binding.root.context as? GlobalPositionActivity  //ACI ACCEDIM AL LAYOUT PRINCIPAL
                    if (activity?.findViewById<View>(R.id.frgMovimientos) != null) { //VEGEM SI EXISTEIX EL frgMovimientos (EN CAS POSITIU ES TABLET)
                        activity.replaceFragmentMovimientos(AccountsMovementFragment.newInstance(cuenta)) // FALTA IMPLEMENTAR EL FRAGMENT
                    } else {
                        val intent = Intent(cvMain.context, MovimientosActivity::class.java)
                        intent.putExtra("cuenta", cuenta)
                        ContextCompat.startActivity(cvMain.context, intent, null)
                    }


                }
            }
        }
    }
}