package com.example.contactos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class AdaptadorCustom(var contexto: Context, items:ArrayList<Contacto>):BaseAdapter() {
   var items:ArrayList<Contacto>?=null
    var copiaItem:ArrayList<Contacto>?=null
    init {
        this.items= ArrayList(items)
        this.copiaItem=items
    }

    override fun getCount(): Int {
        return this.items?.count()!!
    }


    override fun getItem(p0: Int): Any {
        return this.items?.get(p0)!!
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var viewHolder:ViewHolder?=null
        var vista:View?=convertView
        if(vista==null){
            vista =LayoutInflater.from(contexto).inflate(R.layout.template_contactos,null)
            viewHolder=ViewHolder(vista)
            vista.tag=viewHolder

        }else{
            viewHolder=vista.tag as? ViewHolder
        }
        val item=getItem(position) as Contacto
        viewHolder?.nombre?.text=item.nombre +" "+item.apellido
        viewHolder?.empresa?.text=item.empresa
        viewHolder?.foto?.setImageResource(item.foto)

        return vista!!


    }

    fun filtrar(str:String){
        items?.clear()
        if(str.isEmpty()){
            items= ArrayList(copiaItem)
            notifyDataSetChanged()
            return
        }
        var busqueda=str
        busqueda= busqueda.lowercase()
        for(item in copiaItem!!){
            val nombre=item.nombre.lowercase()
            if(nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun addItem(item:Contacto){
            copiaItem?.add(item)
        items= ArrayList(copiaItem)
        notifyDataSetChanged()
    }
    fun removeItem(index:Int){
        copiaItem?.removeAt(index)
        items= ArrayList(copiaItem)
        notifyDataSetChanged()
    }
    fun updateItem(index:Int,newItem:Contacto){
        copiaItem?.set(index,newItem)
        items= ArrayList(copiaItem)
        notifyDataSetChanged()
    }
    private class ViewHolder(vista:View){
        var nombre: TextView?=null
        var empresa: TextView?=null
        var foto: ImageView?=null
        init {
            nombre=vista.findViewById(R.id.txtNombre)
            foto=vista.findViewById(R.id.imgFoto)
            empresa=vista.findViewById(R.id.txtEmpresa)
        }
    }

}