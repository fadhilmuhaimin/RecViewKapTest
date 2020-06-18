package com.pluralsight.recviewkaptest

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val listAdapter = listOf(
            Goddamn("Ini Makanan"),
            Goddamn("Ini Makanan"),
            Goddamn("Ini Makanan"),
            Goddamn("Ini Makanan")
        )
        val foodAdapter = MakananAdapter(listAdapter,this,this,false,rv_main)
        rv_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
//            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = foodAdapter
        }
    }
}
