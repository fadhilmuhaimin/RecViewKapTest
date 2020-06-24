package com.pluralsight.recviewkaptest.Adapter

import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(open val containerView: View) : RecyclerView.ViewHolder(containerView) {
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