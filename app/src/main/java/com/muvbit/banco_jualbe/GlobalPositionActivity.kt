package com.muvbit.banco_jualbe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.muvbit.banco_jualbe.databinding.ActivityGlobalPositionBinding
import com.muvbit.banco_jualbe.fragments.AccountsFragment
import com.muvbit.banco_jualbe.fragments.VacioFragment
import com.muvbit.banco_jualbe.pojo.Cliente


class GlobalPositionActivity : AppCompatActivity() {
    lateinit var binding: ActivityGlobalPositionBinding
    lateinit var cliente: Cliente
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cliente = intent.getSerializableExtra("cliente") as Cliente
        val fragmentCuentas:AccountsFragment=AccountsFragment.newInstance(cliente as Cliente)
        val fragmentPrueba:VacioFragment=VacioFragment.newInstance("hola","queTal")
        replaceFragmentCuentas(fragmentCuentas)
        replaceFragmentMovimientos(fragmentPrueba)
    }

    fun replaceFragmentCuentas(fragmentNuevo: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frgCuentas.id,fragmentNuevo)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun replaceFragmentMovimientos(fragmentNuevo: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        binding.frgMovimientos?.let {
            fragmentTransaction.replace(it.id,fragmentNuevo)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }
    }
