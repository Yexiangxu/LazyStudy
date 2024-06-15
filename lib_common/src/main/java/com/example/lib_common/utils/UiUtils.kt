package com.example.lib_common.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_common.R

object UiUtils {
    private fun initItemDecoration(context:Context): DividerItemDecoration {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_recyclerview_divider)!!)
        return itemDecorator
    }
    public fun initRecyclerView(recyclerView: RecyclerView){
        recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(recyclerView.context)
            addItemDecoration(initItemDecoration(recyclerView.context))
        }
    }
}