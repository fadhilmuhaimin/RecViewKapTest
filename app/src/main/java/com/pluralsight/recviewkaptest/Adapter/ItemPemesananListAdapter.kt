package com.pluralsight.recviewkaptest.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.AlignSelf
import com.google.android.flexbox.FlexboxLayoutManager
import com.kapcake.pos.Listener.AdapterListener
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_item_pemesanan.*
import java.util.*


class ItemPemesananListAdapter(
    private val context: Context,
    private val items: List<ItemPemesanan>,
    private val listener: AdapterListener,
    private val parentHeight: Int,
    private val positionParent: Int
)
    : RecyclerView.Adapter<ItemPemesananListAdapter.ViewHolder>() {

    var allDone = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_pemesanan, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(context,items[position], listener,position,itemCount,parentHeight,positionParent,allDone)
        }
        catch (e : Exception){
            //null data to return
            //test delete local
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : BaseViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(
            context: Context,
            data: ItemPemesanan,
            listener: AdapterListener,
            position: Int,
            itemCount: Int,
            parentHeight: Int,
            positionParent: Int,
            allDone: Boolean
        ) {

            val parentHeight2 = 891
            val cardHeight = 139
            val bottomHeight = 62
            val bottomIndex = 4
            val bottomIndex2 = 6

            val lp: ViewGroup.LayoutParams = flex.getLayoutParams()
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 0.0f
            }


            if (position == (itemCount - 1)) {
                layout_bottom.visibility = View.VISIBLE
                if(allDone){
                    layout_bottom.text = "Pesanan Selesai"
                    layout_bottom.setBackgroundColor(context.resources.getColor(android.R.color.holo_blue_dark))
                    layout_bottom.setTextColor(context.resources.getColor(android.R.color.white))
                }
                else {
                    layout_bottom.text = "Tandai Semua"
                }
            }
            //bottom page pertama
            else if(position<=bottomIndex && position%bottomIndex==0 && position!=0) {
                layout_bottom.visibility = View.VISIBLE
                layout_bottom.text = "Bersambung ->"

//                if(layout_bottom.visibility == View.VISIBLE && position < (itemCount - 1) && layout_bottom.text.toString().contains("bersambung",true)){
//                    val lp: ViewGroup.LayoutParams = flex.getLayoutParams()
//                    if (lp is FlexboxLayoutManager.LayoutParams) {
//                        val flexboxLp = lp
//                        flexboxLp.flexGrow = 1.0f
//                    }
//                }

                if (lp is FlexboxLayoutManager.LayoutParams) {
                    lp.flexGrow = 1.0f
                }
            }
            //bottom page kedua
            else if(position>bottomIndex && (position-bottomIndex)%bottomIndex2==0) {

                Log.d("Anjay",position.toString())
                layout_bottom.visibility = View.VISIBLE
                layout_bottom.text = "Bersambung ->"

//                if(layout_bottom.visibility == View.VISIBLE && position < (itemCount - 1) && layout_bottom.text.toString().contains("bersambung",true)){
                if (lp is FlexboxLayoutManager.LayoutParams) {
                    lp.flexGrow = 1.0f
                }
//                }
            }
            else {
                layout_bottom.visibility = View.GONE
            }

            if(position==0){
                layout_top.visibility = View.VISIBLE
                layout_top2.visibility = View.GONE
                layout_top3.visibility = View.VISIBLE
            }
            //untuk page kedua ada top2 jumlah list lebih dari page pertama
            else if((position>bottomIndex) && ((position-bottomIndex)%bottomIndex2==1)){
                layout_top.visibility = View.GONE
                layout_top2.visibility = View.VISIBLE
                layout_top3.visibility = View.GONE
                layout_top2.text="-> Bersambung"
            }
            else {
                layout_top.visibility = View.GONE
                layout_top2.visibility = View.GONE
                layout_top3.visibility = View.GONE
            }

            tvNama.text = data.nama
            tv_jumlah.text = data.jumlah+"x"
            if(data.ukuran.isBlank()){
                tvSize.visibility = View.GONE
            }
            else{
                tvSize.visibility = View.VISIBLE
                tvSize.text = data.ukuran
            }

            if(data.kondisi.isBlank()){
                tvKondisi.visibility = View.GONE
            }
            else{
                tvKondisi.visibility = View.VISIBLE
                tvKondisi.text = data.kondisi
            }

            if(data.selected) {
                tvNama.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                tvSize.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                tvKondisi.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                tv_jumlah.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
                iv_2.visibility = View.VISIBLE

                card.setOnClickListener(null)
            }
            else {
                //aktifkan kalau misal bermasalah di recyclernya
//                tvNama.setPaintFlags(tvNama.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                iv_2.visibility = View.GONE
                card.setOnClickListener {
                    listener.clickItemPemesanan(data,it,position)
                }
            }



            if(layout_bottom.visibility == View.VISIBLE && layout_bottom.text.toString().toLowerCase(Locale.ROOT)=="tandai semua"){
                layout_bottom.setOnClickListener {
                    listener.clickItemPemesanan(data,it,position)
                }
            }
            else {
                layout_bottom.setOnClickListener(null)
            }


        }
    }
}