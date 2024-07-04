package com.lazyxu.test

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.hypot

//class CircularRevealHelper(context: Context,attrs:AttributeSet):ConstraintHelper(context, attrs) {
//    override fun updatePostLayout(container: ConstraintLayout) {
//        super.updatePostLayout(container)//所有控件布局完成了
//        referencedIds.forEach {
//            val view:View =container.getViewById(it)
//            val radius:Int= hypot(view.width.toDouble(),view.height.toDouble()).toInt()
//            ViewAnimationUtils.createCircularReveal(view,0,0,0f,radius.toFloat())
//                .setDuration(2000L)
//                .start()
//        }
//    }
//}

class CircularRevealHelper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {

    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)
        referencedIds.forEach {
            val view: View =container.getViewById(it)
            val anim = ViewAnimationUtils.createCircularReveal(
                view, view.width / 2,
                view.height / 2, 0f,
                hypot((view.height / 2).toDouble(), (view.width / 2).toDouble()).toFloat()
            )
            anim.duration = 3000
            anim.start()
        }
    }
}
