package com.example.retrofitprac

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitprac.dataClass.CustomModel
import com.example.retrofitprac.databinding.ListViewRowBinding

import kotlin.contracts.contract


class CustomAdapter(
    context: Context,
    obj: CustomModel,
    clickLambda: (Int,CustomModel) -> Unit
) : RecyclerView.Adapter<CustomAdapter.viewHolder>() {

    var obj: CustomModel
    var context: Context
    var clickLambda: (Int,CustomModel) -> Unit
    lateinit var listener: clickListener

    init {
        this.obj = obj
        this.context = context
        this.clickLambda = clickLambda
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val LVRB = ListViewRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(LVRB)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Log.d("#####", "CustomAdapter Line 43 position = $position")
        holder.binding.cardId.text = (position + 1).toString()
        holder.binding.total.text = obj.total.toString()
        holder.binding.skip.text = obj.skip.toString()
        holder.binding.limit.text = obj.limit.toString()
        holder.binding.idInCart.text = obj.carts.get(position).id.toString()
        holder.binding.productId.text = obj.carts.get(position).products.get(0).id.toString()
        holder.binding.totalInCart.text = obj.carts.get(position).total.toString()
        holder.binding.discountedTotal.text = obj.carts.get(position).discountedTotal.toString()
        holder.binding.userId.text = obj.carts.get(position).userID.toString()
        holder.binding.totalProducts.text = obj.carts.get(position).totalProducts.toString()
        holder.binding.totalQuantity.text = obj.carts.get(position).totalQuantity.toString()
//        holder.binding.idInProduct.text = obj.carts.get(position).products.get(position).id.toString()
//        holder.binding.title.text = obj.carts.get(position).products.get(position).title
//        holder.binding.price.text = obj.carts.get(position).products.get(position).price.toString()
//        holder.binding.quantity.text = obj.carts.get(position).products.get(position).quantity.toString()
//        holder.binding.totalInProduct.text = obj.carts.get(position).products.get(position).total.toString()
//        holder.binding.discountedPercentage.text = obj.carts.get(position).products.get(position).discountPercentage.toString()
//        holder.binding.discountedPrice .text = obj.carts.get(position).products.get(position).discountedPrice.toString()

//        val customAdapter = innerCustomAdapter(
//            context.applicationContext,
//            obj,
//            clickLambda,
//            totalInCart,
//            discountedTotal,
//            userId,
//            totalProducts,
//            totalQuantity,
//            size,
//            position
//        )
//        innerRecyclerView.adapter = customAdapter
//        innerRecyclerView.layoutManager = LinearLayoutManager(context.applicationContext)


        Log.d("#####", "CustomAdapter Line 79");

        holder.binding.CL.setOnClickListener(View.OnClickListener {

            //listener.click(position,obj)
            clickLambda(position,obj)

        })
    }

    override fun getItemCount(): Int {
        Log.d("#####", "CustomAdapter Line 90 obj.carts.size = " + obj.carts.size)
        return obj.carts.size
    }

    class viewHolder(binding: ListViewRowBinding) : RecyclerView.ViewHolder(binding.root) {

        //    val tv: TextView = view.findViewById(R.id.textView)
//    val tv2: TextView = view.findViewById(R.id.textView2)
//    val tv3: TextView = view.findViewById(R.id.textView3)
//    val tv4: TextView = view.findViewById(R.id.textView4)
//    val view: View = view
        val binding: ListViewRowBinding

        init {
            this.binding = binding
        }
    }
}

open class clickListener() {

    open fun click(index: Int, obj: CustomModel) {}
}