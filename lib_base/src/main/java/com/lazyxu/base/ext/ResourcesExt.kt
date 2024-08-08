package com.lazyxu.base.ext

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

/**
 * Description: 资源操作相关
 */

fun Context.color(id: Int) = ContextCompat.getColor(this, id)

fun Context.string(id: Int) = resources.getString(id)

fun Context.drawable(id: Int) = ContextCompat.getDrawable(this, id)











