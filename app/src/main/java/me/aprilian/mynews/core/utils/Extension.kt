package me.aprilian.mynews.core.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT){
    message?.let {
        Toast.makeText(this, it, duration).show()
    }
}

fun Context.dpToPx(dp: Int): Int {
    return (this.resources.displayMetrics.density * dp).toInt()
}

fun Context.dpToPxF(dp: Float): Float {
    return this.resources.displayMetrics.density * dp
}