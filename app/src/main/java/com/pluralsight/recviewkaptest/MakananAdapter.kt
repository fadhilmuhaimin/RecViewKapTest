package com.pluralsight.recviewkaptest

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_top.view.*

class MakananAdapter(private val food : List<Goddamn>, private val contexx : Context, private val activity : Activity,private val cek : Boolean, private val recyclerView: RecyclerView) : RecyclerView.Adapter<MakananAdapter.FoodHolder>() {
    class FoodHolder(view : View) :RecyclerView.ViewHolder(view){
        val tv_name = view.atas
        val rv_top = view.rv_nested
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        return FoodHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_top,parent,false))
    }
    override fun getItemCount()= food.size
    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val listAdapter = listOf(
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar"),
            Food("1","Ayam Bakar")
        )
        if (cek == false){
            val foodAdapter = FoodAdapter(listAdapter,contexx,activity,recyclerView)
            holder.rv_top.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
                adapter = foodAdapter
            }
            holder.tv_name.text = food[position].name
        }else{

            val foodAdapter = FoodAdapter(listAdapter,contexx,activity,recyclerView)
            holder.rv_top.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
                adapter = foodAdapter
            }
            holder.tv_name.text = "lanjut"
        }
    }
}