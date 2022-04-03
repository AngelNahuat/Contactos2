package com.example.contactos

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    var lista:ListView?=null
    var grid:GridView?=null
    var switchCambiaVista:ViewSwitcher?=null

companion object{
    var contacto:ArrayList<Contacto>?=null
    var adapter:AdaptadorCustom?=null
    var adaptergrid:AdaptadorGrid?=null
    fun agregarCon(contactos: Contacto){
        adapter?.addItem(contactos)
    }

    fun obtenerContacto(index:Int):Contacto{
        return adapter?.getItem(index) as Contacto
    }
    fun eliminarContacto(index:Int){
        adapter?.removeItem(index)
    }
    fun actualizarContacto(index:Int,nuevoContacto: Contacto){
        adapter?.updateItem(index,nuevoContacto)
    }
}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        contacto= ArrayList()
        contacto!!.add(Contacto("Angel","Nahuat","Amazon",18,55.0F,"Felipe Carrillo Puerto 77200","9831152335","nglnahuat@gmail.com",R.drawable.foto_01))
         lista=findViewById<ListView>(R.id.lista)
        grid=findViewById<GridView>(R.id.grid)
         adapter=AdaptadorCustom(this, contacto!!)
        adaptergrid= AdaptadorGrid(this, contacto!!)
        switchCambiaVista=findViewById<ViewSwitcher>(R.id.cambiaVista)
        lista?.adapter=adapter
        grid?.adapter= adaptergrid

        lista?.setOnItemClickListener { parent, view, position, id->

            val intent=Intent(this, Detalle::class.java)
            intent.putExtra("ID",position.toString())
            startActivity(intent)


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val search=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val buscar=menu?.findItem(R.id.searchview)
        val searchView=buscar?.actionView as SearchView

        val itemswitch=menu?.findItem(R.id.switchCambia)
        itemswitch?.setActionView(R.layout.switch_item)
        val switchView=itemswitch?.actionView?.findViewById<Switch>(R.id.switchCambia)

        searchView.setSearchableInfo(search.getSearchableInfo(componentName))
        searchView.queryHint="Buscar contacto"
        searchView.setOnQueryTextFocusChangeListener{v,hasFocus->{

        }
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filtrar(newText!!)
             return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }
        })
            switchView?.setOnCheckedChangeListener{buttonView,isChecked->
switchCambiaVista?.showNext()
            }
        }



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.newview->{
                val intent=Intent(this,Nuevo::class.java)
                startActivity(intent)
                return true

            }else->{ return super.onOptionsItemSelected(item)}
        }

        }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }

}