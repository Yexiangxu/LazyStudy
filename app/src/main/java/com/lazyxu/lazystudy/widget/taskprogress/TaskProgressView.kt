package com.lazyxu.lazystudy.widget.taskprogress

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazyxu.base.ext.dp2px
import com.lazyxu.lazystudy.ShortPlayYouXuanTask
import com.lazyxu.lazystudy.databinding.ViewTaskProgressBinding

class TaskProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    private var mViewBinding: ViewTaskProgressBinding
    private val taskProgressAdapter by lazy {
        TaskProgressAdapter()
    }

    companion object {
        private const val ANIMTIME_DEFAULT = 1000L
    }

    init {
        mViewBinding = ViewTaskProgressBinding.inflate(LayoutInflater.from(context), this, true)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mViewBinding.rvProgress.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = taskProgressAdapter
        }
    }

    private fun setList(list: List<ShortPlayYouXuanTask>) {
        taskProgressAdapter.setList(list)
    }

    private fun receivableCoins(

        tasks: List<ShortPlayYouXuanTask>, todayMins: Int
    ): Int {
        var receivableCoins = 0
        for (task in tasks) {
            if (task.mins in 0..todayMins) {
                receivableCoins += task.coins
            }
        }
        return receivableCoins
    }

    private fun findMaxTaskIndex(todayMins: Int, tasks: List<ShortPlayYouXuanTask>): Int {
        var maxTaskIndex = -1

        for ((index, task) in tasks.withIndex()) {
            if (todayMins >= task.mins) {
                maxTaskIndex = index
            } else {
                break
            }
        }

        return maxTaskIndex
    }

    fun setListProgressCoin(
        list: List<ShortPlayYouXuanTask>,
        receive_mins: Int,
        today_mins: Int
    ) {
        val recyclerViewParams = mViewBinding.rvProgress.layoutParams
        recyclerViewParams.width =
            (context.resources.getDimension(com.lazyxu.base.R.dimen.dp_70) * (list.size)).toInt()
        mViewBinding.rvProgress.layoutParams = recyclerViewParams
        taskProgressAdapter.setList(list)
        taskProgressAdapter.setChosePosition(receive_mins)
        val progressIndex=findMaxTaskIndex(today_mins,list)+1
        setProgress((mViewBinding.pbTask.max)/(list.size)*progressIndex, receivableCoins(list, today_mins))
    }

    private fun setProgress(progress: Int, coinNumb: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mViewBinding.pbTask.setProgress(progress, true)
        } else {
            mViewBinding.pbTask.progress = progress
        }
        mViewBinding.pbTask.post {
            val progress = mViewBinding.pbTask.progress
            val max = mViewBinding.pbTask.max

            // Calculate the target position
            val pbWidth = mViewBinding.pbTask.width
            val targetX = (progress / max.toFloat()) * pbWidth

            // Get initial position of tvProgress
            val tvLocation = IntArray(2)
            mViewBinding.tvProgress.getLocationOnScreen(tvLocation)
            val tvStartX = tvLocation[0]

            // Get position of pbTask
            val pbLocation = IntArray(2)
            mViewBinding.pbTask.getLocationOnScreen(pbLocation)
            val pbStartX = pbLocation[0]

            // Calculate translationX
            var translationX =
                pbStartX + targetX - tvStartX - mViewBinding.tvProgress.width + 20.dp2px
            val parentEndPos = pbStartX + pbWidth - tvStartX - mViewBinding.tvProgress.width
            val parentStartPos = (pbStartX - tvStartX).toFloat()
            var translationIvX = translationX
            if (translationX > parentEndPos) {
                translationX = parentEndPos.toFloat()
                translationIvX = translationX + 20.dp2px
            } else if (translationX < parentStartPos) {
                translationX = parentStartPos
                translationIvX = translationX - 20.dp2px
            }
            val animator =
                ObjectAnimator.ofFloat(mViewBinding.tvProgress, "translationX", translationX)
            animator.duration = ANIMTIME_DEFAULT // Duration in milliseconds
            animator.start()
            val animatorIv =
                ObjectAnimator.ofFloat(mViewBinding.ivDown, "translationX", translationIvX)
            animatorIv.duration = ANIMTIME_DEFAULT // Duration in milliseconds
            animatorIv.start()
            val valueAnimator = ValueAnimator.ofInt(0, coinNumb)
            valueAnimator.duration = ANIMTIME_DEFAULT // Duration in milliseconds

            valueAnimator.addUpdateListener { animator ->
                val animatedValue = animator.animatedValue as Int
                mViewBinding.tvProgress.text = "已攒${animatedValue}金币"
            }
            valueAnimator.start()
        }
    }

}