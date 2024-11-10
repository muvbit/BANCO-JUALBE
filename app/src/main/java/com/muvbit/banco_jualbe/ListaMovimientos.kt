package com.muvbit.banco_jualbe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muvbit.banco_jualbe.databinding.ListaMovimientosBinding

class ListaMovimientos : AppCompatActivity() {
    lateinit var binding:ListaMovimientosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ListaMovimientosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run{

        }

        }
    }
