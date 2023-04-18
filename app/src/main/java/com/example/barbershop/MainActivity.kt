package com.example.barbershop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.barbershop.databinding.ActivityMainBinding
import com.example.barbershop.view.Home
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        /*if (v.id == R.id.button_login) {
            val name = binding.editName.text.toString()
            val password = binding.editPassword.text.toString()

            when {
                name.isEmpty() -> {
                    message(v, "Favor preencher o campo NOME!")
                }
                password.isEmpty() -> {
                    message(v, "Favor preencher o campo SENHA!")
                }
                password.length < 6 -> {
                    message(v, "A senha precisa ter no mínimo 6 dígitos!")
                }
                else -> {

                }
            }
        }*/
        handleLogin(v)
    }

    private fun handleLogin(v: View) {
        val name = binding.editName.text.toString()
        val password = binding.editPassword.text.toString()

        when {
            name.isEmpty() -> {
                message(v, getString(R.string.error_name))
            }
            password.isEmpty() -> {
                message(v, getString(R.string.error_password))
            }
            password.length < 6 -> {
                message(v, getString(R.string.invalid_password))
            }
            else -> {
                doLogin(name)
            }
        }
    }

    private fun message(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

        //Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun doLogin(name: String) {
        val intent = Intent(this, Home::class.java)
        intent.putExtra("name", name)
        startActivity(intent)

    }
}