package com.lazyxu.base.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int,
    private val includeEdge: Boolean
) : ItemDecoration() {
    constructor(
        spanCount: Int,
        spacing: Int,
        includeEdge: Boolean
    ) : this(spanCount, spacing, spacing, includeEdge)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            // 左右间距计算
            outRect.left = horizontalSpacing - column * horizontalSpacing / spanCount
            outRect.right = (column + 1) * horizontalSpacing / spanCount

            // 上下间距计算
            if (position < spanCount) { // 第一行，添加顶部间距
                outRect.top = verticalSpacing
            }
            outRect.bottom = verticalSpacing // 所有 item 添加底部间距
        } else {
            // 左右间距计算
            outRect.left = column * horizontalSpacing / spanCount
            outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount

            // 上下间距计算
            if (position >= spanCount) {
                outRect.top = verticalSpacing // 从第二行开始添加顶部间距
            }
        }
    }
}

