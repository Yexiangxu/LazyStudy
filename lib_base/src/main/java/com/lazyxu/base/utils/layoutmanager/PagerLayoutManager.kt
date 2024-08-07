package com.lazyxu.base.utils.layoutmanager

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.lazyxu.base.log.LogUtils

/**
 * 单页滑动LinearLayoutManager
 */
class PagerLayoutManager : LinearLayoutManager {
    private var mPagerSnapHelper: PagerSnapHelper? = null
    private var mOnViewPagerListener: OnViewPagerListener? = null
    private var mRecyclerView: RecyclerView? = null
    private var mDrift = 0 //位移，用来判断移动方向

    companion object {
        private const val TAG = "PagerLayoutManager"
    }

    constructor(context: Context?, orientation: Int) : super(context, orientation, false) {
        init()
    }

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
        init()
    }

    private fun init() {
        mPagerSnapHelper = PagerSnapHelper()
    }

    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        LogUtils.d(TAG, "onAttachedToWindow")
        mPagerSnapHelper?.attachToRecyclerView(view)
        this.mRecyclerView = view
        mRecyclerView?.addOnChildAttachStateChangeListener(mChildAttachStateChangeListener)
    }

    /**
     * 滑动状态的改变
     * 缓慢拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 空闲状态-> SCROLL_STATE_IDLE
     *
     * @param state
     */
    override fun onScrollStateChanged(state: Int) {
        when (state) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                LogUtils.d(TAG, "滑动结束 childCount=$childCount")
                val viewIdle = mPagerSnapHelper?.findSnapView(this)
                if (viewIdle != null) {
                    val positionIdle = getPosition(viewIdle)
                    mOnViewPagerListener?.onPageSelected(mDrift >= 0, positionIdle, viewIdle)
                    if (positionIdle == itemCount - 1) {
                        mOnViewPagerListener?.onPreLoadMore(positionIdle, viewIdle)
                    }
                    LogUtils.d(TAG, "滑动结束 childCount=$childCount")
                } else {
                    LogUtils.d(TAG, "滑动结束 null")
                }
            }

            RecyclerView.SCROLL_STATE_DRAGGING -> {
                LogUtils.d(TAG, "缓慢拖拽")
            }

            RecyclerView.SCROLL_STATE_SETTLING -> {
                LogUtils.d(TAG, "快速滚动")
            }
        }
    }

    /**
     * 监听竖直方向的相对偏移量
     *
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        mDrift = dy
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    /**
     * 监听水平方向的相对偏移量
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        mDrift = dx
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    fun setOnViewPagerListener(listener: OnViewPagerListener?) {
        mOnViewPagerListener = listener
    }

    private val mChildAttachStateChangeListener: RecyclerView.OnChildAttachStateChangeListener =
        object : RecyclerView.OnChildAttachStateChangeListener {
            /**
             * itemView依赖Window
             */
            override fun onChildViewAttachedToWindow(view: View) {
                LogUtils.d(
                    TAG,
                    "onChildViewAttachedToWindow ${getPosition(view)},mRecyclerView=${mRecyclerView},childCount=$childCount"
                )
                if (childCount == 1) {
                    mOnViewPagerListener?.onPageSelected(true, getPosition(view), view)
                }
            }

            /**
             * itemView脱离Window
             */
            override fun onChildViewDetachedFromWindow(view: View) {
                if (mDrift >= 0) {
                    LogUtils.d(TAG, "onPageRelease========true")
                    mOnViewPagerListener?.onPageRelease(
                        true,
                        getPosition(view),
                        view
                    )
                } else {
                    LogUtils.d(TAG, "onPageRelease========false")
                    mOnViewPagerListener?.onPageRelease(
                        false,
                        getPosition(view),
                        view
                    )
                }
            }
        }
}