package com.muvbit.banco_jualbe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.muvbit.banco_jualbe.R
import com.muvbit.banco_jualbe.cuentasAdapters.CuentasAdapter
import com.muvbit.banco_jualbe.dao.CuentaDAO
import com.muvbit.banco_jualbe.databinding.FragmentAccountsBinding
import com.muvbit.banco_jualbe.globalPositionAdapters.AccountsAdapter
import com.muvbit.banco_jualbe.pojo.Cliente
import com.muvbit.banco_jualbe.pojo.Cuenta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "cliente"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Cliente? = null
    private var cliente:Cliente=Cliente()
    lateinit var binding: FragmentAccountsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cliente = it.getSerializable(ARG_PARAM1) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAccountsBinding.inflate(layoutInflater)
        initRecyclerView()
        return binding.root

    }

    private fun initRecyclerView() {
        binding.run {
            cuentasRV.layoutManager = LinearLayoutManager(this@AccountsFragment.context)
            cuentasRV.adapter = AccountsAdapter(CuentaDAO().getCuentas(cliente) as ArrayList<Cuenta>)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(c: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1,c)
                }
            }
    }
}