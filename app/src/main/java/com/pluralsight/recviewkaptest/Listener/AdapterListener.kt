package com.kapcake.pos.Listener

import android.bluetooth.BluetoothDevice
import android.view.View
import com.pluralsight.recviewkaptest.ItemPemesanan

open interface AdapterListener {

    fun clickItemPemesanan(data: ItemPemesanan, view: View,position: Int){}
//    fun clickOrder(data: ItemPemesanan, view: View){}
//    fun clickBluetoothDevice(data: BluetoothDevice){}
}