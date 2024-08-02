package com.lazyxu.lazystudy

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class WrapContentLinearLayoutManager(context: Context) :
    LinearLayoutManager(context, HORIZONTAL, false) {
    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        var width = 0
        var height = 0
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            measureChild(view, widthSpec, heightSpec)
            width += view.measuredWidth
            height = Math.max(height, view.measuredHeight)
        }
        setMeasuredDimension(width, height)
    }
}