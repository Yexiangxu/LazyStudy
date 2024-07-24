package com.lazyxu.lazystudy

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.lazyxu.base.base.fragment.BaseVbFragment
import com.lazyxu.lazystudy.databinding.FragmentMineBinding

class MineFragment : BaseVbFragment<FragmentMineBinding>() {
    private val taskProgressAdapter by lazy {
        TaskProgressAdapter()
    }

    override fun initView() {
        mViewBinding.customProgressBar.setProgress(0, 2000)

        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(100, 2000)
//            mViewBinding.seekbar.setProgress(100/(taskProgressAdapter.data.size)*2f)
            mViewBinding.seekbar.setProgress(100f)
        }, 2000L)
        // 示例：更新进度条进度
        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(1000, 2000)
        }, 4000L)
        Handler(Looper.getMainLooper()).postDelayed({
            mViewBinding.customProgressBar.setProgress(2000, 2000)
        }, 6000L)
        initRecyclerView()

    }

    override fun initClicks() {
    }

    private fun initRecyclerView() {
        mViewBinding.rvProgress.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            layoutManager =
                object : LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return false // 禁用横向滚动
                    }

                    override fun canScrollVertically(): Boolean {
                        return false // 禁用纵向滚动
                    }
                }
            adapter = taskProgressAdapter
        }

        taskProgressAdapter.setList(
            listOf(
                ShortPlayYouXuanTask(mins = 1, coins = 1),
                ShortPlayYouXuanTask(mins = 5, coins = 5),
                ShortPlayYouXuanTask(mins = 15, coins = 15),
                ShortPlayYouXuanTask(mins = 30, coins = 30),
                ShortPlayYouXuanTask(mins = 60, coins = 60),
                ShortPlayYouXuanTask(mins = 120, coins = 120),
                ShortPlayYouXuanTask(mins = 150, coins = 150)

            )
        )
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