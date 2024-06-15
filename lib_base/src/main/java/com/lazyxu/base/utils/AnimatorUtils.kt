package com.lazyxu.base.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Path
import android.graphics.PathMeasure
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.lazyxu.base.R
import com.lazyxu.base.ext.dp2pxFloat

object AnimatorUtils {
    private fun ValueAnimator.setting(): ValueAnimator {
        this.repeatCount = 10
        this.repeatMode = ValueAnimator.RESTART
        //设置动画时间
        this.duration = 200
        this.interpolator = LinearInterpolator()
        return this
    }

    fun doCartAnimator(startView: View, cartView: View, parentView: ViewGroup, listener: OnAnimatorListener?) {
        /**
         * 设置移动的view
         */
        val goods = ImageView(parentView.context)
        goods.setPadding(1, 1, 1, 1)
        //图片切割方式
        goods.scaleType = ImageView.ScaleType.CENTER_CROP
        //获取图片资源
//        goods.setImageResource(R.drawable.default_home1)
        //设置RelativeLayout容器(这里必须设置RelativeLayout 设置LinearLayout动画会失效)
        val params = RelativeLayout.LayoutParams( 21.dp2pxFloat.toInt(), 26.dp2pxFloat.toInt())
        //把动画view添加到动画层
        parentView.addView(goods, params)

        //第二步:
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        val parentLocation = IntArray(2)
        //获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
        parentView.getLocationInWindow(parentLocation)
        val startLoc = IntArray(2)
        //获取商品图片在屏幕中的位置
        startView.getLocationInWindow(startLoc)
        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        val endLoc = IntArray(2)
        cartView.getLocationInWindow(endLoc)

        //第三步:
        //正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        val startX = (startLoc[0] - parentLocation[0] + startView.width / 2).toFloat() // 动画开始的X坐标
        val startY = (startLoc[1] - parentLocation[1] + startView.height / 2).toFloat() //动画开始的Y坐标

        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        val toX = (endLoc[0] - parentLocation[0] + cartView.width / 5).toFloat()
        val toY = (endLoc[1] - parentLocation[1]).toFloat()

        //第四步:
        //计算中间动画的插值坐标，绘制贝塞尔曲线
        val path = Path()
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY)
        //第一个起始坐标越大，贝塞尔曲线的横向距离就会越大 toX,toY:为终点
        path.quadTo((startX + toX) / 2, startY, toX, toY)
        val pathMeasure = PathMeasure(path, false)
        //实现动画具体博客可参考 鸿洋大神的https://blog.csdn.net/lmj623565791/article/details/38067475
        val valueAnimator = ValueAnimator.ofFloat(0f, pathMeasure.length).setting()

        valueAnimator.addUpdateListener { animation ->
            //更新动画
            val value = animation.animatedValue as Float
            val currentPosition = FloatArray(2)
            pathMeasure.getPosTan(value, currentPosition, null)
            goods.translationX = currentPosition[0] //改变了ImageView的X位置
            goods.translationY = currentPosition[1] //改变了ImageView的Y位置
        }

        //第五步:
        //开始执行动画
        valueAnimator.start()

        //第六步:
        //对动画添加监听
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                //onAnimationStart()方法会在动画开始的时候调用
            }

            //onAnimationEnd()方法会在动画结束的时候调用
            override fun onAnimationEnd(animation: Animator) {
                //把移动的图片imageView从父布局里移除
                parentView.removeView(goods)
                listener?.onAnimationEnd(animation)
            }

            override fun onAnimationCancel(animation: Animator) {
                //onAnimationCancel()方法会在动画被取消的时候调用
            }

            override fun onAnimationRepeat(animation: Animator) {
                //onAnimationRepeat()方法会在动画重复执行的时候调用
            }
        })
    }

    interface OnAnimatorListener {
        fun onAnimationEnd(animator: Animator?)
    }
}