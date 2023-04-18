package com.example.barbershop.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var date: String = ""
    private var hour: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val name = intent.extras?.getString("name").toString()

        val datePicker = binding.datePicker

        datePicker.setOnDateChangedListener { _, year, monthOfYar, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYar)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var day = dayOfMonth.toString()

            if (dayOfMonth < 10) {
                day = "0$dayOfMonth"
            }
            val month: String = if (monthOfYar < 10) {
                "" + (monthOfYar + 1)
            } else {
                (monthOfYar + 1).toString()
            }

            date = "$day / $month / $year"
        }

        val timePicker = binding.timePicker

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val min: String = if (minute < 10) {
                "0$minute"
            } else {
                minute.toString()
            }

            hour = "$hourOfDay:$min" //19:00
        }

        binding.timePicker.setIs24HourView(true) // formato de 24hrs

        binding.buttonConfirmarAgendar.setOnClickListener {
            val profissional1 = binding.checkBoxProfissional1
            val profissional2 = binding.checkBoxProfissional2
            val profissional3 = binding.checkBoxProfissional3

            when {
                hour.isEmpty() -> {
                    messagem(it, "Escolha um horário!", "#FF0000")

                }
                hour < "8:00" && hour > "19:00" -> {
                    messagem(
                        it,
                        "Horário de atendimento das 08:00 as 19:00",
                        "#FF0000"
                    )
                }
                date.isEmpty() -> {
                    messagem(it, "Escolha uma data!", "#FF0000")
                }
                profissional1.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    messagem(it, "Agendamento Realizado com sucesso!", "#FF03DAC5")
                }
                profissional2.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    messagem(it, "Agendamento Realizado com sucesso!", "#FF03DAC5")
                }
                profissional3.isChecked && date.isNotEmpty() && hour.isNotEmpty() -> {
                    messagem(it, "Agendamento Realizado com sucesso!", "#FF03DAC5")
                }
                else -> {
                    messagem(it, "Escolha um profissional", "#FF0000")
                }
            }
        }
    }

    private fun messagem(view: View, message: String, color: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(color))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}