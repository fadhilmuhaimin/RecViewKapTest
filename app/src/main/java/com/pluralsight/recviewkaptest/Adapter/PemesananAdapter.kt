package com.pluralsight.recviewkaptest.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.kapcake.pos.Listener.AdapterListener
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.Pemesanan
import com.pluralsight.recviewkaptest.R
import com.pluralsight.recviewkaptest.Session
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_pemesanan.*


class PemesananAdapter(private val context: Context, private val items: List<Pemesanan>, private val listener: AdapterListener)
    : RecyclerView.Adapter<PemesananAdapter.ViewHolder>() {

    var parentHeight : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_pemesanan, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(context,items[position], listener,position,parentHeight)
        }
        catch (e : Exception){
            //null data to return
            //test delete local
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer, AdapterListener {

        private var adapterItem : ItemPemesananAdapter? = null
        private var listItem : MutableList<ItemPemesanan>? = ArrayList()

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(
            context: Context,
            data: Pemesanan,
            listener: AdapterListener,
            position: Int,
            parentHeight: Int
        ) {

            listItem?.clear()
            listItem?.addAll(data.item)


            adapterItem = listItem?.let { ItemPemesananAdapter(context, it,this,parentHeight,position) }
            val layoutManager2 = FlexboxLayoutManager(context)
            layoutManager2.flexDirection = FlexDirection.COLUMN
            layoutManager2.justifyContent = JustifyContent.FLEX_START
            layoutManager2.flexWrap = FlexWrap.WRAP
//            layoutManager2.alignContent = AlignContent.FLEX_END

            recycler.apply {
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                layoutManager = layoutManager2
                adapter = adapterItem
            }
        }

        override fun clickItemPemesanan(data: ItemPemesanan, view: View, position: Int) {
            super.clickItemPemesanan(data, view, position)
            when(view.id){
                R.id.card -> {

                    data.selected = !data.selected
                    adapterItem?.notifyItemChanged(position)

                    //cek kalau sudah complete alldone
                    if(listItem?.any { it.selected==false }==false) {
                        adapterItem?.allDone = true
                        adapterItem?.notifyDataSetChanged()
                    }
                    else if(adapterItem?.allDone == true) {
                        adapterItem?.allDone = false
                        adapterItem?.notifyDataSetChanged()
                    }

                }
                R.id.layout_bottom -> {
                    listItem?.forEach {
                        it.selected = true
                    }

                    adapterItem?.allDone = true
                    adapterItem?.notifyDataSetChanged()
                }
            }

//            adapterItem?.notifyDataSetChanged()
        }
    }
}