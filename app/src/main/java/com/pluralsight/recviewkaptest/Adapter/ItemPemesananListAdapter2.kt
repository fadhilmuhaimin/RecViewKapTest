package com.pluralsight.recviewkaptest.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kapcake.pos.Listener.AdapterListener
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_item_pemesanan.*


class ItemPemesananListAdapter2(
    private val context: Context,
    private val items: List<ItemPemesanan>,
    private val listener: AdapterListener,
    private val parentHeight: Int
)
    : RecyclerView.Adapter<ItemPemesananListAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_pemesanan, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            holder.bind(context,items[position], listener,position,itemCount,parentHeight)
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

        var a = 0

        fun lastItem(i : Int?): Int? {
            if (i != null) {
                a=i
            }
            return a
        }

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(
            context: Context,
            data: ItemPemesanan,
            listener: AdapterListener,
            position: Int,
            itemCount: Int,
            parentHeight: Int
        ) {



            if(parentHeight!=0) {
                layout_bottom.viewTreeObserver.addOnGlobalLayoutListener {
//                    if(layout_bottom.isVisible) {
//                        if(containerView.bottom!=0 && layout_bottom.bottom!=0) {
//                            Log.d("Anjay2", containerView.bottom.toString())
//                            Log.d("Anjay2", layout_bottom.bottom.toString())
//                        }
//                    }
                }

                containerView.viewTreeObserver.addOnGlobalLayoutListener {

//                    card.waitForLayout {

                        if (position == (itemCount - 1)) {
                            layout_bottom.visibility = View.VISIBLE
                            layout_bottom.text = "Tandai Semua"
                        } else if ((containerView.bottom + (139 + 62)) > 891) {
//                            Log.d("Anjay", position.toString())
//                            Log.d("Anjay", containerView.height.toString())
//                            Log.d("Anjay", containerView.bottom.toString())

                            Log.d("Anjay2", position.toString())
                            Log.d("Anjay2", lastItem(null).toString())
                            lastItem(position)

                            layout_bottom.visibility = View.VISIBLE
                            layout_bottom.text = "Bersambung ->"

//                            if(containerView.bottom!=0 && layout_bottom.bottom!=0) {
                            if(containerView.bottom!=0) {
//                                Log.d("Anjay2", containerView.height.toString())
//                                Log.d("Anjay2", containerView.bottom.toString())
//                                Log.d("Anjay2", layout_bottom.bottom.toString())

                                val sisaSpace = 891-(containerView.bottom+62)
                                if(sisaSpace>0){
    //                                Log.d("Anjay2", position.toString())
    //                                Log.d("Anjay2", containerView.bottom.toString())
    //                                Log.d("Anjay2", sisaSpace.toString())

                                    val parameter = layout_bottom.getLayoutParams() as LinearLayout.LayoutParams
                                    parameter.setMargins(
                                        parameter.leftMargin,
                                        parameter.topMargin+sisaSpace,
                                        parameter.rightMargin,
                                        parameter.bottomMargin
                                    ) // left, top, right, bottom

                                    layout_bottom.setLayoutParams(parameter)


                                }
                            }



                        } else {
                            layout_bottom.visibility = View.GONE
                        }

    ////                containerView.waitForLayout {
    //                    // do whatever
    //                    if(position==0){
    //                        Log.d("Anjay2", position.toString())
    //                        Log.d("Anjay2", containerView.bottom.toString())
    //                    }
    //
    //
    //                    if((containerView.bottom + (139)) > parentHeight){
    ////
    ////                        Log.d("Anjay23", parentHeight.toString())
    ////                        Log.d("Anjay", parentHeight.toString())
    //
    ////                        setMarginBottom(card,100)
    ////
    //                        layout_bottom.visibility = View.VISIBLE
    //                        layout_bottom.text = "Bersambung ->"
    ////                        layout_bottom.marginBottom = "Bersambung ->"
    //                    }


                        if(containerView.top==0 && position!=0) {
                            layout_top2.visibility = View.VISIBLE
                            layout_top2.text="-> Bersambung"
                        }
                        else
                            layout_top2.visibility = View.GONE
//                    }
                }

//                layout_bottom.viewTreeObserver.addOnGlobalLayoutListener {
//                    Log.d("Anjay23", layout_bottom.height.toString())
//                }
//
//
//
////                containerView.waitForLayout {
////                    //139 nanti diganti pakai minimum child height + padding top & bottom
////
////                }
            }

            layout_top2.visibility = View.GONE

            if(position==0){
                layout_top.visibility = View.VISIBLE
            }
            else {
                layout_top.visibility = View.GONE
            }

//            tvNama.text = data.nama
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

            tvNama.text="<strike>"+data.nama+"</strike>"
            tvNama.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)



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




        }


    }


}