package com.pluralsight.recviewkaptest.Activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapcake.pos.Listener.AdapterListener
import com.pluralsight.recviewkaptest.Adapter.PemesananAdapter
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.Pemesanan
import com.pluralsight.recviewkaptest.R
import com.pluralsight.recviewkaptest.Session
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterListener {

    var arrayList : MutableList<ItemPemesanan> = ArrayList()
    var arrayList3 : MutableList<ItemPemesanan> = ArrayList()
    var arrayList4 : MutableList<ItemPemesanan> = ArrayList()
    var arrayList5 : MutableList<ItemPemesanan> = ArrayList()
    var arrayList2 : MutableList<Pemesanan> = ArrayList()
    var foodAdapter : PemesananAdapter? = null
    var session : Session? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        session = Session(this)
//        session?.hasFilled(false)                                                                 //mainkan ini kalau mau liat tampilkan halaman intro
        if(session?.isFilled==false) {
            session?.checkFilled()
            finish()
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE

        arrayList.add(ItemPemesanan("10","Kopi Susu","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("5","Burger","Panas-Dingin","Panas"))
        arrayList.add(ItemPemesanan("9","Pizza","Panas","Panas"))
        arrayList.add(ItemPemesanan("2","Teh","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("10","Kopi Susu","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("5","Burger","Panas-Dingin","Panas"))
        arrayList.add(ItemPemesanan("9","Pizza","Panas","Panas"))
        arrayList.add(ItemPemesanan("2","Teh","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("8","Green tea","Dingin","Cup Besar"))
        arrayList.add(ItemPemesanan("4","Sup Paru","Panas","Besar"))
        arrayList.add(ItemPemesanan("6","Coto","Panas","Besar"))
        arrayList.add(ItemPemesanan("3","Salad","Panas","Besar"))
        arrayList.add(ItemPemesanan("4","Sup Paru","Panas","Besar"))
        arrayList.add(ItemPemesanan("6","Coto","Panas","Besar"))
        arrayList.add(ItemPemesanan("9","Pizza","",""))
        arrayList.add(ItemPemesanan("8","Green tea","Dingin","Cup Besar"))
        arrayList.add(ItemPemesanan("4","Sup Paru","Panas","Besar"))
        arrayList.add(ItemPemesanan("6","Coto","Panas","Besar"))
        arrayList.add(ItemPemesanan("3","Salad","Panas","Besar"))
        arrayList.add(ItemPemesanan("4","Sup Paru","Panas","Besar"))
        arrayList.add(ItemPemesanan("6","Coto","Panas","Besar"))
        arrayList.add(ItemPemesanan("9","Pizza","",""))
        arrayList4.add(ItemPemesanan("1","Susu","Dingin","Cup Besar"))
        arrayList4.add(ItemPemesanan("2","Teh","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("10","Kopi Susu","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("5","Burger","Panas-Dingin",""))
        arrayList4.add(ItemPemesanan("9","Pizza","",""))
        arrayList4.add(ItemPemesanan("1","Susu","Dingin","Cup Besar"))
        arrayList4.add(ItemPemesanan("2","Teh","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("10","Kopi Susu","Panas","Cup Sedang"))
        arrayList4.add(ItemPemesanan("5","Burger","Panas-Dingin",""))
        arrayList4.add(ItemPemesanan("9","Pizza","",""))
        arrayList4.add(ItemPemesanan("8","Green tea","Dingin","Cup Besar"))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin","Panas"))
        arrayList5.add(ItemPemesanan("9","Pizza","Panas","Panas"))
        arrayList5.add(ItemPemesanan("1","Susu","Dingin","Cup Besar"))
        arrayList5.add(ItemPemesanan("3","Salad","Panas","Besar"))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin",""))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin","Panas"))
        arrayList5.add(ItemPemesanan("9","Pizza","Panas","Panas"))
        arrayList5.add(ItemPemesanan("1","Susu","Dingin","Cup Besar"))
        arrayList5.add(ItemPemesanan("3","Salad","Panas","Besar"))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin",""))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin","Panas"))
        arrayList5.add(ItemPemesanan("9","Pizza","Panas","Panas"))
        arrayList5.add(ItemPemesanan("1","Susu","Dingin","Cup Besar"))
        arrayList5.add(ItemPemesanan("3","Salad","Panas","Besar"))
        arrayList5.add(ItemPemesanan("5","Burger","Panas-Dingin",""))
        arrayList2.add(Pemesanan("Pelanggan A","Dine In",arrayList))
        arrayList2.add(Pemesanan("Pelanggan A","Dine In",arrayList3))
        arrayList2.add(Pemesanan("Pelanggan A","Dine In",arrayList4))
        arrayList2.add(Pemesanan("Pelanggan A","Dine In",arrayList5))
//        arrayList2.add(Pemesanan("Pelanggan B","Dine In",arrayList3))

        foodAdapter = PemesananAdapter(this, arrayList2, this)
        rv_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            adapter = foodAdapter
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        foodAdapter?.parentHeight = root.bottom
        foodAdapter?.notifyDataSetChanged()
    }

    inline fun View.waitForLayout(crossinline f: () -> Unit) = with(viewTreeObserver) {
        if(this.isAlive){
            addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    f()
                }
            })
        }
    }
}
