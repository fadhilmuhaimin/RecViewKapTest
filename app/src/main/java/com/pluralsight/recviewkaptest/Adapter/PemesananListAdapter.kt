package com.pluralsight.recviewkaptest.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.kapcake.pos.Listener.AdapterListener
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.Pemesanan
import com.pluralsight.recviewkaptest.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_pemesanan.*


class PemesananListAdapter(private val context: Context, private val items: List<Pemesanan>, private val listener: AdapterListener)
    : RecyclerView.Adapter<PemesananListAdapter.ViewHolder>() {

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

        private var adapterItem : ItemPemesananListAdapter? = null
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


//            tvHarga.text = GeneralHelper.convertRupiah(data.total?.toDouble()?:0.0)
//            tvId.text = "#"+data.no_pemesanan.toString()
//
//            var jumlah = 0
//            for (item in data.item){
//                jumlah+=item.jumlah?:0
//            }
//            tvJumlah.text = "$jumlah item"
//            tvWaktu.text = data.waktu_simpan
//
//            if(data.nama_meja?.isNotBlank()==true){
//                tvMeja.text = data.nama_meja+"-"+data.nama_kategori_meja
//                tvMeja.visibility = View.VISIBLE
//            }
//            else {
//                tvMeja.visibility = View.GONE
//            }
//
//            if(data.is_selected) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    root.setBackgroundColor(context.resources.getColor(R.color.gray6,null))
//                }
//                else {
//                    root.setBackgroundColor(context.resources.getColor(R.color.gray6))
//                }
//            }
//            else {
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    root.setBackgroundColor(context.resources.getColor(android.R.color.white,null))
//                }
//                else {
//                    root.setBackgroundColor(context.resources.getColor(android.R.color.white))
//                }
//            }
//
//            containerView.setOnClickListener {
//                viewModel.updateCurrentPemesanan(data)
//            }



            adapterItem = listItem?.let { ItemPemesananListAdapter(context, it,this,parentHeight,position) }
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
                    data.selected = true
                    adapterItem?.notifyItemChanged(position)

                    //cek kalau sudah complete alldone
                    if(listItem?.any { it.selected==false }==false) {
                        adapterItem?.allDone = true
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