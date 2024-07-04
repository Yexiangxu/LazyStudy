package com.lazyxu.base.utils.decoration

import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 普通条目间距
 * 可参考  [DividerItemDecoration] 源码
 */
class NormalItemDecoration(private val mOrientation: Int = LinearLayoutManager.VERTICAL) :
    RecyclerView.ItemDecoration() {

    private val mDivider = ColorDrawable()
    private var removeStartEndMargin = false//去除两边边距

    fun setBounds(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
        mDivider.bounds.left = left
        mDivider.bounds.top = top
        mDivider.bounds.right = right
        mDivider.bounds.bottom = bottom
    }

    fun setColor(@ColorInt color: Int) {
        mDivider.color = color
    }

    /**
     * 是否去除前后 第一个item的开始间距和最后一个item的结束间距
     */
    fun removeStartEndMargin(lastBottom: Boolean) {
        removeStartEndMargin = lastBottom
    }


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val lastPosition = state.itemCount - 1
        if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.top = mDivider.bounds.top
            outRect.left = if (position == 0) 0 else mDivider.bounds.left
            outRect.right = if (position == lastPosition) 0 else mDivider.bounds.right
            outRect.bottom = mDivider.bounds.bottom
        } else {
            outRect.top = if (position == 0) 0 else mDivider.bounds.top
            outRect.left = mDivider.bounds.left
            outRect.right = mDivider.bounds.right
            outRect.bottom = if (position == lastPosition) 0 else mDivider.bounds.bottom
        }
    }
}