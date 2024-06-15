package com.lazyxu.base.widget


import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.lazyxu.base.R

/**
 * User:Lazy_xu
 * Date:2024/05/29
 * Description:  手机号登录，搜索都可以用
 * FIXME
 */
class ClearEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle//保留AppCompatEditText相关属性
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private var drawleft = if (compoundDrawables[0] != null) {
        compoundDrawables[0]
    } else {
        compoundDrawablesRelative[0]
    }
    private var drawright = if (compoundDrawables[2] != null) {
        compoundDrawables[2]
    } else {
        compoundDrawablesRelative[2]
    }

    private var clearDrawable: Drawable? = null
    private val endDrawable = if (drawright != null) {
        drawright
    } else {
        clearDrawable
    }

    init {
        clearDrawable = ContextCompat.getDrawable(context, R.drawable.svg_et_clear)
        isSingleLine = true
        clearDrawable?.setBounds(
            0,
            0,
            clearDrawable?.intrinsicWidth ?: 0,
            clearDrawable?.intrinsicHeight ?: 0
        )
        // Add text watcher to show/hide clear button
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                showClearButton(s.isNotEmpty())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        // Initially hide the clear button
        showClearButton(false)
    }

    private fun showClearButton(show: Boolean) {
        setCompoundDrawables(
            drawleft,
            compoundDrawables[1],
            if (show) endDrawable else null,
            compoundDrawables[3]
        )
    }


    /**
     * totalPaddingRight
     */

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (endDrawable != null) {
                val touchable =
                    event.x > width - totalPaddingRight && event.x < width - paddingRight
                if (touchable) {
                    text?.clear()
                }
            }

        }
        return super.onTouchEvent(event)
    }
}