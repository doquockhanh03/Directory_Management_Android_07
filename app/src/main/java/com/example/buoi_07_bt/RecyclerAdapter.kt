package com.example.buoi_07_bt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var context: Context, var array: ArrayList<Document>): RecyclerView.Adapter<RecyclerAdapter.ItemHolder>() {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var content: TextView = itemView.findViewById(R.id.tv_content)
    }

    //
    var onItemClick:((Document, Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_document, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val Index = array.get(position)

        holder.title.text = Index.title
        holder.content.text = Index.content

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(array[position], position)
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}