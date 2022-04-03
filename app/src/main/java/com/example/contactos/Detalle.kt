package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Detalle : AppCompatActivity() {
    var index:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val index = intent.getStringExtra("ID")?.toInt()
        Log.d("INDEX", index.toString())

        mapData()



    }
    fun mapData(){

        val contacto = MainActivity.obtenerContacto(index!!)
        val nombre = findViewById<TextView>(R.id.nombre)

        val empresa = findViewById<TextView>(R.id.empresa)
        val edad = findViewById<TextView>(R.id.edad)
        val peso = findViewById<TextView>(R.id.peso)
        val telefono = findViewById<TextView>(R.id.telefono)
        val correo = findViewById<TextView>(R.id.correo)
        val direccion = findViewById<TextView>(R.id.direccion)
        val foto=findViewById<ImageView>(R.id.foto)

        nombre.text = contacto.nombre + " " + contacto.apellido

        empresa.text = contacto.empresa
        edad.text = contacto.edad.toString() + " años"
        peso.text = contacto.peso.toString() + " kg"
        telefono.text = contacto.telefono
        correo.text = contacto.correo
        direccion.text = contacto.direccion
        foto.setImageResource(contacto.foto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.editarcontacto -> {
                val intent=Intent(this, Nuevo::class.java)
                intent.putExtra("ID",index.toString())
                startActivity(intent)
                return true
            }
            R.id.eliminar -> {
                MainActivity.eliminarContacto(index)
                finish()
                return true

            }


        else->{ return super.onOptionsItemSelected(item) }
    }
}

    override fun onResume() {
        super.onResume()
        mapData()
    }

}



