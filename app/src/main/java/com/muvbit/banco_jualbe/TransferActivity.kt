package com.muvbit.banco_jualbe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.widget.addTextChangedListener
import com.google.android.material.slider.Slider.OnChangeListener
import com.google.android.material.snackbar.Snackbar
import com.muvbit.banco_jualbe.databinding.TransferBinding
import java.util.zip.Inflater

class TransferActivity : AppCompatActivity() {
    lateinit var binding: TransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val misCuentas = arrayOf(
            "ES70 2100 1937 80 099138774",
            "ES42 0082 2332 74 7689374016",
            "ES28 1024 2334 74 9288374021",
            "ES25 1223 8554 22 8432001928"
        )
        val divisas = arrayOf("€", "$", "£")
        val adapterCuentas = ArrayAdapter(this, android.R.layout.simple_spinner_item, misCuentas)
        val adapterDivisas = ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        val spnCuenta1 = binding.spnCuenta
        val spnCuenta2 = binding.spnCuenta2
        val rdCuentas = binding.rdCuentas
        var btnCancelar = binding.cancelar
        var btnEnviar = binding.enviar
        val cantidad = binding.txCantidad
        val cuentaAjena = binding.txCuentaAjena
        val spnDivisa = binding.divisa
        var rdAjenaChecked = false
        btnEnviar.isEnabled = false
        rdCuentas.setOnCheckedChangeListener { _, checkedID ->
            if (checkedID == binding.rdCPropia.id) {
                binding.spnCuenta2.visibility = View.VISIBLE
                binding.txCuentaAjena.visibility = View.INVISIBLE
                rdAjenaChecked = false
            } else {
                rdAjenaChecked = true
                binding.spnCuenta2.visibility = View.INVISIBLE
                binding.txCuentaAjena.visibility = View.VISIBLE
            }

        }

        adapterCuentas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterDivisas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCuenta1.adapter = adapterCuentas
        spnCuenta2.adapter = adapterCuentas
        spnDivisa.adapter = adapterDivisas
        var cuenta1Seleccionada=""
        spnCuenta1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cuenta1Seleccionada = misCuentas[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                cuenta1Seleccionada = ""
            }

        })
        var cuenta2Seleccionada=""
        spnCuenta2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cuenta2Seleccionada = misCuentas[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        })
        var divisaSeleccionada=""
        spnDivisa.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                divisaSeleccionada = divisas[position].toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        })

        fun checkFields(): Boolean {
            btnEnviar.isEnabled =
                !rdAjenaChecked && !cantidad.text.isNullOrEmpty() || rdAjenaChecked && !cuentaAjena.text.isNullOrEmpty() && !cantidad.text.isNullOrEmpty()
            return btnEnviar.isEnabled
        }

        cuentaAjena.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && cuentaAjena.text.isNullOrEmpty()) {
                cuentaAjena.setError("El campo no puede estar vacío.")
            }
            checkFields()
        }

        cantidad.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && cantidad.text.isNullOrEmpty()) {
                cuentaAjena.setError("El campo no puede estar vacío")
            }
        }
        cantidad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                checkFields()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        btnEnviar.setOnClickListener {
            var todoBien = checkFields()
            if (!todoBien) {
                snackbarMacker("Revise los campos antes de enviar")
            }else{
                var mensaje="La transferencia ha sido satisfactoria"+
                        "\nCuenta de origen:\n"+cuenta1Seleccionada+
                        "\nCuenta destino:\n"+if(rdAjenaChecked)cuentaAjena.text else cuenta2Seleccionada+
                        "\nCantidad: "+cantidad.text+divisaSeleccionada
                AlertDialog.Builder(this).setMessage(mensaje).setPositiveButton("Aceptar"){ dialog,_ ->
                        dialog.dismiss()
                    }.show()
                }
            }


        btnCancelar.setOnClickListener {
            cantidad.text?.clear()
            cuentaAjena.text?.clear()
        }

    }

    private fun snackbarMacker(mensaje: String) {
        val transferSnackbar = Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG)
        transferSnackbar.show()
    }


}