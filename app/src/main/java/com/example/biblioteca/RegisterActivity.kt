package com.example.biblioteca

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.Toast
import com.example.biblioteca.databinding.ActivityMainBinding
import com.example.biblioteca.databinding.ActivityRegisterBinding
import com.example.biblioteca.fecha.Date
import com.example.biblioteca.perfil.Pefil
import java.time.Month

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerVinculo: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerVinculo = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerVinculo.root)
        title = ""

        val preferences = getSharedPreferences("access", Context.MODE_PRIVATE)

        registerVinculo.date.setOnClickListener{ showDate() }

        registerVinculo.regis.setOnClickListener {
            val editor = preferences.edit()
            var nombres = registerVinculo.name.text.toString()
            val email = registerVinculo.correo.text.toString()
            var password = registerVinculo.claveR.text.toString()
            editor.putString("nombre",nombres)
            editor.putString("usuario",email)
            editor.putString("clave",password)
            editor.putString("sesion","1")
            editor.apply()
            Toast.makeText(this, "Registrado con exito", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun showDate() {
        val datePicker = Date{day, month, year -> dateSelected(day, month, year)}
        datePicker.show(supportFragmentManager,"datePicker")
    }

    fun dateSelected(day:Int, month:Int, year:Int){
        registerVinculo.date.setText(" Dia: $day Mes: $month Año: $year")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true //super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.perfil -> startActivity(Intent(this, Pefil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}