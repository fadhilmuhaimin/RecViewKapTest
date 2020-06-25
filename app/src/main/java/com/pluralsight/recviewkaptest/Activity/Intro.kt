package com.pluralsight.recviewkaptest.Activity

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.pluralsight.recviewkaptest.Adapter.IntroItemPemesananAdapter
import com.pluralsight.recviewkaptest.ItemPemesanan
import com.pluralsight.recviewkaptest.R
import com.pluralsight.recviewkaptest.Session
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.row_item_pemesanan.*

class Intro : AppCompatActivity() {

    var arrayList : MutableList<ItemPemesanan> = ArrayList()
    var adapterIntro : IntroItemPemesananAdapter? = null

    lateinit var session : Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        session = Session(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        adapterIntro = arrayList.let { IntroItemPemesananAdapter(this, it) }
        val layoutManager2 = FlexboxLayoutManager(this)
        layoutManager2.flexDirection = FlexDirection.COLUMN
        layoutManager2.justifyContent = JustifyContent.FLEX_START
        layoutManager2.flexWrap = FlexWrap.WRAP

        recycler.apply {
            layoutManager = layoutManager2
            adapter = adapterIntro
        }

        //pindah ke halaman utama by button klik
//        layout_left.setOnClickListener {
//            startActivity(Intent(this@Intro,MainActivity::class.java))
//            finish()
//        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        //hitung card sama layout bottom
        adapterIntro?.parentHeight = root.height
        adapterIntro?.cardHeigt = card.height
        adapterIntro?.bottomHeight = layout_bottom.height
        adapterIntro?.session = session

        //hilangkan measure layout awal, lanjut hitung jumlah card
        layout_left.visibility = View.GONE
        setupDummy()
    }

    fun setupDummy(){
        arrayList.clear()
        arrayList.add(ItemPemesanan("10", "Kopi Susu", "Panas", "Cup Sedang"))
        arrayList.add(ItemPemesanan("5", "Burger", "Panas-Dingin", "Panas"))
        arrayList.add(ItemPemesanan("9", "Pizza", "Panas", "Panas"))
        arrayList.add(ItemPemesanan("2", "Teh","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("10", "Kopi Susu", "Panas", "Cup Sedang"))
        arrayList.add(ItemPemesanan("5", "Burger", "Panas-Dingin", "Panas"))
        arrayList.add(ItemPemesanan("9", "Pizza", "Panas", "Panas"))
        arrayList.add(ItemPemesanan("2", "Teh", "Panas", "Cup Sedang"))
        arrayList.add(ItemPemesanan("7", "Cappucino", "Panas", "Cup Sedang"))
        arrayList.add(ItemPemesanan("9", "Pizza", "Panas", "Panas"))
        arrayList.add(ItemPemesanan("2", "Teh","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("7","Cappucino","Panas","Cup Sedang"))
        arrayList.add(ItemPemesanan("10", "Kopi Susu", "Panas", "Cup Sedang"))
        arrayList.add(ItemPemesanan("5", "Burger", "Panas-Dingin", "Panas"))

        arrayList[0].selected = true
        arrayList[5].selected = true
        arrayList[8].selected = true
        adapterIntro?.notifyDataSetChanged()
    }
}