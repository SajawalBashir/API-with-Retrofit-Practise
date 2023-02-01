package com.example.retrofitprac

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import kotlin.contracts.contract

class CustomAdapter(context: Context, arrayList: ArrayList<CustomModelElement>, clickLambda: (Int) -> Unit) : RecyclerView.Adapter<viewHolder>() {

    var arrayList: ArrayList<CustomModelElement>
    var context: Context
    var clickLambda: (Int)-> Unit
    lateinit var listener: clickListener
    init {
        this.arrayList = arrayList
        this.context = context
        this.clickLambda=clickLambda
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflator = LayoutInflater.from(context)
        val view = inflator.inflate(R.layout.list_view_row, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.tv.text = "id = " + arrayList.get(position).id
        holder.tv2.text = "title = " + arrayList.get(position).title
        holder.tv3.text = "userId = " + arrayList.get(position).userId
        holder.tv4.text = "Complete Status = " + arrayList.get(position).completed

        Log.d("jjj","Line 57");

        holder.view.setOnClickListener(View.OnClickListener {

            listener.click(position)
            //clickLambda(position)

        })

    }

    override fun getItemCount(): Int {
        Log.d("jjj","line 51 arrayList size = " + arrayList.size)
        return arrayList.size
    }

}

class viewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tv: TextView = view.findViewById(R.id.textView)
    val tv2: TextView = view.findViewById(R.id.textView2)
    val tv3: TextView = view.findViewById(R.id.textView3)
    val tv4: TextView = view.findViewById(R.id.textView4)
    val view: View = view
}

open class clickListener() {

    open fun click(index: Int){}
}