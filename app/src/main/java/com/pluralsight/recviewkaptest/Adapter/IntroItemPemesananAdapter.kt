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
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.R
import com.pluralsight.recviewkaptest.Session
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_item_pemesanan.*


class IntroItemPemesananAdapter(
    private val context: Context,
    private val items: List<ItemPemesanan>)
    : RecyclerView.Adapter<IntroItemPemesananAdapter.ViewHolder>() {

    var parentHeight: Int = 0
    var cardHeigt: Int = 0
    var bottomHeight: Int = 0
    var session: Session? = null
    var temp: MutableList<Int>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_pemesanan, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(context,items[position], position,itemCount,parentHeight,cardHeigt,bottomHeight,session,temp)
        }
        catch (e : Exception){
            //null data to return
            //test delete local
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : BaseViewHolder(containerView), LayoutContainer {

        fun setMarginBottom(v: View, bottom: Int) {
            val params = v.layoutParams as MarginLayoutParams
            params.setMargins(
                params.leftMargin, params.topMargin,
                params.rightMargin, bottom
            )
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(
            context: Context,
            data: ItemPemesanan,
            position: Int,
            itemCount: Int,
            parentHeight: Int,
            cardHeigt: Int,
            bottomHeight: Int,
            session: Session?,
            temp: MutableList<Int>?
        ) {
            if(parentHeight!=0) {
                containerView.viewTreeObserver.addOnGlobalLayoutListener {
                        if (position == (itemCount - 1)) {
                            layout_bottom.visibility = View.VISIBLE
                            layout_bottom.text = "Tandai Semua"

                            //ambil nilai pertama dan kedua dari perubahan terakhir utk disimpan di shared preferences
                            if(temp?.size?:0 >= 2) {
                                val firstPage = temp?.get(0) ?: 0
                                val nextPage = temp?.get(1)?.minus(temp.get(0))?:0
                                session?.setFirstPageSize(firstPage)
                                session?.setNextPageSize(nextPage)
                                session?.hasFilled(true)
                            }
                            temp?.clear()

                        } else if ((containerView.bottom + (cardHeigt + bottomHeight)) > parentHeight) {

                            layout_bottom.visibility = View.VISIBLE
                            layout_bottom.text = "Bersambung ->"
                            //simpan posisi bottom ke array
                            temp?.add(position)
                        } else {
                            layout_bottom.visibility = View.GONE
                        }

                        if(containerView.top==0 && position!=0) {
                            layout_top2.visibility = View.VISIBLE
                            layout_top2.text="-> Bersambung"
                        }
                        else
                            layout_top2.visibility = View.GONE
                }
            }

            layout_top2.visibility = View.GONE

            if(position==0){
                layout_top.visibility = View.VISIBLE
            }
            else {
                layout_top.visibility = View.GONE
            }

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
            }
            else {
                //aktifkan kalau misal bermasalah di recyclernya
//                tvNama.setPaintFlags(tvNama.getPaintFlags() and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                iv_2.visibility = View.GONE
            }

        }
    }
}