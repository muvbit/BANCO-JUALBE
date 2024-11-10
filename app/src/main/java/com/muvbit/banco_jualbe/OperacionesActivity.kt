package com.muvbit.banco_jualbe

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muvbit.banco_jualbe.databinding.OperacionesBinding

class OperacionesActivity : AppCompatActivity() {
    lateinit var binding:OperacionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=OperacionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run{
            cvMovimientos.setOnClickListener{

            }
        }
        }
    }
