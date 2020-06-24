package com.kapcake.pos.Listener

interface DialogListener {
    fun onDataReceived(from:String,data: String){}
    fun onBayarReceived(kodePemesanan: String,tunai: Int, kembalian: Int, metode: String){}
}