package com.example.barbershop.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barbershop.R
import com.example.barbershop.adapter.ServicesAdapter
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.model.Services


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicesAdapter: ServicesAdapter
    private val listServices: MutableList<Services> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val name = intent.extras?.getString("name")
        binding.textUserName.text = "Bem vindo, $name"

        val recyclerViewServices = binding.recyclerViewServices
        recyclerViewServices.layoutManager = GridLayoutManager(this, 2)
        servicesAdapter = ServicesAdapter(this, listServices)
        recyclerViewServices.setHasFixedSize(true)
        recyclerViewServices.adapter = servicesAdapter

        getServices()

        binding.buttonAgendar.setOnClickListener{
            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }

    private fun getServices() {
        val service1 = Services(R.drawable.img1, "Corte de cabelo")
        listServices.add(service1)

        val service2 = Services(R.drawable.img2, "Corte de barba")
        listServices.add(service2)

        val service3 = Services(R.drawable.img3, "Lavagem de cabelo")
        listServices.add(service3)

        val service4 = Services(R.drawable.img4, "Tratamento de cabelo")
        listServices.add(service4)
    }

}