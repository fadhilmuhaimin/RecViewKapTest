package com.pluralsight.recviewkaptest

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

class FoodAdapter(private val food : List<Food>, private val contexx : Context, private val activity : Activity, private val recyclerView: RecyclerView): RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    class FoodHolder(view : View) :RecyclerView.ViewHolder(view){
         val tv_name = view.tv_nama
        val tv_jumlah = view.tv_jumlah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun getItemCount()= food.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        for (a in food.indices){
            if (a<=5){
                holder.tv_name.text = food[position].name
                holder.tv_jumlah.text = food[position].item
                Log.d("a = ",a.toString())
            }else{
                val listAdapter = listOf(
                    Goddamn("Lanjut"))
                val foodAdapter = MakananAdapter(listAdapter, contexx,activity,true,recyclerView)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    adapter = foodAdapter
                }
            }
        }
    }
}


