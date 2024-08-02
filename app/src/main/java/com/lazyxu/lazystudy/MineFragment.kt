package com.lazyxu.lazystudy

import android.os.Handler
import android.os.Looper
import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.lazystudy.databinding.FragmentMineBinding
import com.lazyxu.lazystudy.widget.taskprogress.TaskProgressAdapter

class MineFragment : BaseVbFragment<FragmentMineBinding>() {
    override fun initView() {
        mViewBinding.customProgressBar.setProgress(0, 2000)

        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(100, 2000)


        }, 2000L)
        // 示例：更新进度条进度
        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(1000, 2000)
        }, 4000L)
        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(2000, 2000)
        }, 6000L)
        mViewBinding.viewTaskProgress.setListProgressCoin(
            listOf(
                ShortPlayYouXuanTask(mins = 1, coins = 88),
                ShortPlayYouXuanTask(mins = 5, coins = 188),
                ShortPlayYouXuanTask(mins = 15, coins = 288),
                ShortPlayYouXuanTask(mins = 30, coins = 388),
                ShortPlayYouXuanTask(mins = 60, coins = 588),
                ShortPlayYouXuanTask(mins = 120, coins = 888),
                ShortPlayYouXuanTask(mins = 150, coins = 988)

            ), 0, 14
        )
    }


    override fun initClicks() {
    }


    override fun onResume() {
        super.onResume()
        mViewBinding.rpvTask.resumePlay()
    }

    override fun onPause() {
        super.onPause()
        mViewBinding.rpvTask.pausePlay()
    }
}