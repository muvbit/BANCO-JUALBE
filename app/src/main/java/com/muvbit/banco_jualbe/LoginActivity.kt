package com.muvbit.banco_jualbe

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.muvbit.banco_jualbe.bd.MiBancoOperacional
import com.muvbit.banco_jualbe.databinding.LoginBinding
import com.muvbit.banco_jualbe.pojo.Cliente

class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val banco=MiBancoOperacional.getInstance(this)
        var clientePrueba:Cliente

        binding.entrar.setOnClickListener {
            val usuario = binding.user.editText?.text.toString()
            val contraseña = binding.password.editText?.text.toString()
            if (usuario.isEmpty()) {
                showSnackbar("El usuario no puede estar vacío.")
                return@setOnClickListener
            }
            if (contraseña.isEmpty()) {
                showSnackbar("La contraseña no puede estar vacía.")
                return@setOnClickListener
            }
            clientePrueba=Cliente(0,usuario,"","",contraseña,"")
            var cliente=banco?.login(clientePrueba)
            if(cliente!=null){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("cliente" , cliente)
                startActivity(intent)
                finish()
            }else{
                AlertDialog.Builder(this).setMessage("El nombre de usuario y/o contraseña no coinciden.").setPositiveButton("Aceptar"){dialog,_->dialog.dismiss()}.show()
                return@setOnClickListener
            }
        }


        binding.tvNuevoCliente.setOnClickListener{
            val intent = Intent(this,NuevoClienteActivity::class.java)
            startActivity(intent)
        }



        binding.salir.setOnClickListener {
            finish()
        }
    }

    private fun showSnackbar(mensaje: String) {
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG).show()
    }
}
