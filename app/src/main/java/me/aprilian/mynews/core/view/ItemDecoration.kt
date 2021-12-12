package me.aprilian.mynews.core.view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.aprilian.mynews.core.utils.dpToPx

class ItemDecoration(context: Context, space: Int = 16) : RecyclerView.ItemDecoration() {

    private val spaceInDp = context.dpToPx(space)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.left = spaceInDp
        outRect.right = spaceInDp
        outRect.bottom = spaceInDp
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceInDp
        }
    }
}
