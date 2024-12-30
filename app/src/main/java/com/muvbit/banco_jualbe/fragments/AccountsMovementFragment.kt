package com.muvbit.banco_jualbe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.dao.CuentaDAO
import com.muvbit.banco_jualbe.dao.MovimientoDAO
import com.muvbit.banco_jualbe.databinding.FragmentAccountsMovementBinding
import com.muvbit.banco_jualbe.globalPositionAdapters.AccountsAdapter
import com.muvbit.banco_jualbe.globalPositionAdapters.MovementsAdapter
import com.muvbit.banco_jualbe.pojo.Cuenta
import com.muvbit.banco_jualbe.pojo.Movimiento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "cuenta"


/**
 * A simple [Fragment] subclass.
 * Use the [AccountsMovementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsMovementFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Cuenta? = null
    private var cuenta:Cuenta?=Cuenta()
    private lateinit var movimientosList:ArrayList<Movimiento>
    lateinit var binding:FragmentAccountsMovementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cuenta=Cuenta()
        arguments?.let {
            cuenta = it.getSerializable(ARG_PARAM1) as Cuenta
        }
        movimientosList=MovimientoDAO().getMovimientos(cuenta) as ArrayList<Movimiento>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAccountsMovementBinding.inflate(layoutInflater)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.run {
            movimientosRV.layoutManager = LinearLayoutManager(this@AccountsMovementFragment.context)
            movimientosRV.adapter = MovementsAdapter(movimientosList)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountsMovementFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(cuenta:Cuenta) =
            AccountsMovementFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, cuenta)
                }
            }
    }
}