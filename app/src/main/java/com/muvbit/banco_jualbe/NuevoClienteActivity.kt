package com.muvbit.banco_jualbe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.muvbit.banco_jualbe.databinding.NuevoclienteBinding

class NuevoClienteActivity : AppCompatActivity() {
    lateinit var binding: NuevoclienteBinding
    var passwordsCoinciden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NuevoclienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

            btnConfirmar.isEnabled = false

            nif.editText?.addTextChangedListener(createTextWatcher(nif))
            password1.editText?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) {
                        password1.error = "Este campo no puede estar vacío."
                    } else {
                        password1.error = null
                    }
                    checkElements()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
            password2.editText?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) {
                        password2.error = "Este campo no puede estar vacío."
                        passwordsCoinciden = false
                    } else if (password1.editText?.text.toString() != password2.editText?.text.toString()) {
                        password2.error = "Las contraseñas no coinciden."
                        passwordsCoinciden = false
                    } else {
                        password2.error = null
                        passwordsCoinciden = true
                    }
                    checkElements()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            nombre.editText?.addTextChangedListener(createTextWatcher(nombre))
            apellidos.editText?.addTextChangedListener(createTextWatcher(apellidos))
            email.editText?.addTextChangedListener(createTextWatcher(email))

            btnVolver.setOnClickListener { finish() }
        }
    }

    private fun checkElements() {
        binding.run {
            btnConfirmar.isEnabled = !nif.editText?.text.isNullOrEmpty() &&
                    passwordsCoinciden &&
                    !nombre.editText?.text.isNullOrEmpty() &&
                    !apellidos.editText?.text.isNullOrEmpty() &&
                    !email.editText?.text.isNullOrEmpty()
        }
    }
    // Creem una funció creadora de TextWatcher al que li pasem el TextInputLayout i així
    // el podem utilitzar per a totes les caixes de text de la vista

    private fun createTextWatcher(layout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    layout.error = "Este campo no puede estar vacío."
                } else {
                    layout.error = null
                }
                checkElements()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}
