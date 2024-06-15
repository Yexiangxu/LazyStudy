package com.lazyxu.base.ext

import android.graphics.Outline
import android.os.SystemClock
import android.view.View
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

/**
 * 视图切圆角
 */
fun View.setClipViewCornerRadius(radius: Int) {
    if (this == null || radius <= 0) return
    this.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, radius.toFloat())
        }
    }
    this.clipToOutline = true
}


private const val MIN_CLICK_DELAY_TIME = 1000
private var lastClickTime = 0L

/**
 * 防重点击
 */
fun View.setOnNoDoubleClickListener(callback: () -> Unit) {
    setOnClickListener {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime
            callback.invoke()
        }
    }
}


/**
 * 点击发送等关闭软键盘
 */
fun View.hideKeyBoard() {
    val imm =
        this.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * 点击去评论等弹出软键盘
 */
fun View.showKeyBoard() {
    this.requestFocus()
    val imm =
        this.context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}



//fun View.showSnackbar(snackbarText: String, timeLength: Int) {
//    Snackbar.make(this, snackbarText, timeLength).run {
//        addCallback(object : Snackbar.Callback() {
//            override fun onShown(sb: Snackbar?) {
//                EspressoIdlingResource.increment()
//            }
//
//            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                EspressoIdlingResource.decrement()
//            }
//        })
//        show()
//    }
//}
//
///**
// * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
// */
//fun View.setupSnackbar(
//    lifecycleOwner: LifecycleOwner,
//    snackbarEvent: LiveData<SingleEvent<Any>>,
//    timeLength: Int) {
//    snackbarEvent.observe(lifecycleOwner, Observer { event ->
//        event.getContentIfNotHandled()?.let {
//            when (it) {
//                is String -> {
//                    hideKeyboard()
//                    showSnackbar(it, timeLength)
//                }
//                is Int -> {
//                    hideKeyboard()
//                    showSnackbar(this.context.getString(it), timeLength)
//                }
//                else -> {
//                }
//            }
//
//        }
//    })
//}
//
//fun View.showToast(
//    lifecycleOwner: LifecycleOwner,
//    ToastEvent: LiveData<SingleEvent<Any>>,
//    timeLength: Int
//) {
//
//    ToastEvent.observe(lifecycleOwner, Observer { event ->
//        event.getContentIfNotHandled()?.let {
//            when (it) {
//                is String -> Toast.makeText(this.context, it, timeLength).show()
//                is Int -> Toast.makeText(this.context, this.context.getString(it), timeLength).show()
//                else -> {
//                }
//            }
//        }
//    })
//}
//

/**
 * 如果需要避免在主线程上进行耗时的文本布局计算，特别是处理长文本或复杂文本时，使用 getTextFuture 方法
 */
fun AppCompatTextView.setTextFutureExt(text: String) =
    setTextFuture(
        PrecomputedTextCompat.getTextFuture(
            text,
            TextViewCompat.getTextMetricsParams(this),
            null
        )
    )

/**
 * 如果需要立即在当前线程上设置文本，并且文本不太长或不复杂，使用 create 方法
 */
fun AppCompatEditText.setTextFutureExt(text: String) =
    setText(
        PrecomputedTextCompat.create(text, TextViewCompat.getTextMetricsParams(this))
    )
