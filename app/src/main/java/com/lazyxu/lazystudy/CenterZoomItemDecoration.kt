package com.lazyxu.lazystudy

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CenterZoomItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        // 设置左右间距
        if (position == 0) {
            outRect.left = spacing
        } else if (position == itemCount - 1) {
            outRect.right = spacing
        } else {
            outRect.left = spacing / 2
            outRect.right = spacing / 2
        }

        // 设置上下间距
        outRect.top = spacing / 2
        outRect.bottom = spacing / 2
    }
}
