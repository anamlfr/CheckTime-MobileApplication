package com.example.checktime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checktime.databinding.ActivityPrincipalBinding


class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_principal)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAcessar.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btRegistrar.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

    }
}