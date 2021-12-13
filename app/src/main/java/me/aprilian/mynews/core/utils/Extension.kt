package me.aprilian.mynews.core.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.Glide

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

fun Context.getDrawableWrapper(drawableId: Int): Drawable? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        try {
            VectorDrawableCompat.create(resources, drawableId, this.theme)
        } catch (ex: Resources.NotFoundException) {
            ContextCompat.getDrawable(this, drawableId)
        }
    } else {
        ContextCompat.getDrawable(this, drawableId)
    }
}

private fun Context.isValidGlideContext() = this !is Activity || (!this.isDestroyed && !this.isFinishing)

//NOTE: use loader/Glide instead using XML to avoid OOM (memory leak) because the image that we (old app) use mostly are PNG instead SVG
fun ImageView.load(context: Context, image:Any?, placeholder: Int? = null){

    // Check context is valid or not to avoid error: "You cannot start a load for a destroyed activity"
    if (!context.isValidGlideContext()) return

    val placeholderDrawable = if (placeholder != null) try {
        //add try catch to avoid OOM when load drawable in lower device
        context.getDrawableWrapper(placeholder)
    } catch (e: Exception){
        null
    } else null

    Glide.with(context)
        .load(image)
        .placeholder(placeholderDrawable)
        .into(this)
}


fun String.clearHtmlTag(): String {
    return this.replace("(?s)<(\\w+)\\b[^<>]*>.*?</\\1>".toRegex(), "")
}

fun View.gone(){
    this.visibility = View.GONE
}