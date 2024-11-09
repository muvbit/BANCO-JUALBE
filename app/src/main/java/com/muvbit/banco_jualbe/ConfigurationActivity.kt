package com.muvbit.banco_jualbe
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.muvbit.banco_jualbe.databinding.ConfigurationBinding
import com.muvbit.banco_jualbe.pojo.Cliente

class ConfigurationActivity : AppCompatActivity() {
    lateinit var binding: ConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.configuration)

        binding = ConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente=intent.getSerializableExtra("cliente") as Cliente
        var contraseñaActualCoincide=false
        var contraseñasNuevasCoinciden=false
        var contraseñaNueva=""

        binding.currentPassword.editText?.setOnFocusChangeListener{_, hasFocus->
            if(!hasFocus){
                val contraseñaActualIntroducida= binding.currentPassword.editText?.text.toString()
                if(cliente.getClaveSeguridad().toString()!=contraseñaActualIntroducida){
                    showSnackbar(getString(R.string.currentPassword_mismatch))
                    contraseñaActualCoincide=false
                }else contraseñaActualCoincide = true
            }
        }
        binding.newPassword2.editText?.setOnFocusChangeListener{_, hasFocus->
            if(!hasFocus){
                var contraseñaNueva= binding.newPassword.editText?.text.toString()
                var contraseñaNueva2=binding.newPassword2.editText?.text.toString()
                if(contraseñaNueva!=contraseñaNueva2){
                    showSnackbar(getString(R.string.newPasswords_mismatch))
                    contraseñasNuevasCoinciden=false
                }else contraseñasNuevasCoinciden = true
            }
        }
        binding.btnConfirmar.setOnClickListener {
            if(contraseñaActualCoincide && contraseñasNuevasCoinciden){
                cliente.setClaveSeguridad(contraseñaNueva)
                showSnackbar(getString(R.string.newPassword_changed))
                finish()
            }else{
                if(!contraseñaActualCoincide)
                showSnackbar(getString(R.string.currentPassword_mismatch))
                if(!contraseñasNuevasCoinciden){
                    showSnackbar(getString(R.string.newPasswords_mismatch))
                }
            }
        }
        binding.btnVolver.setOnClickListener {
            finish()
        }
    }
    private fun showSnackbar(mensaje:String){
        val snackbar=Snackbar.make(binding.root,mensaje, Snackbar.LENGTH_LONG)
        snackbar.anchorView=binding.root.findViewById(R.id.tituloConfiguracion)
        snackbar.show()
    }
}