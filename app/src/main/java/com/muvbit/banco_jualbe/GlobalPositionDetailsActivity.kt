package com.muvbit.banco_jualbe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muvbit.banco_jualbe.databinding.ActivityGlobalPositionBinding
import com.muvbit.banco_jualbe.databinding.ActivityGlobalPositionDetailsBinding

class GlobalPositionDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityGlobalPositionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}