//package com.lazyxu.function
//
//import android.os.Handler
//import android.os.Looper
//import com.lazyxu.base.base.fragment.BaseVbFragment
//import com.lazyxu.base.ext.loadCircle
//import com.lazyxu.lazystudy.databinding.FragmentMineBinding
//
//class MineFragment : BaseVbFragment<FragmentMineBinding>() {
//    override fun initView() {
//        mViewBinding.ivHead.loadCircle(
//            "https://p9-pc-sign.douyinpic.com/tos-cn-p-0015/f5de5023134848e99a0b455b0d2a7f47~tplv-dy-resize-origshort-autoq-75:330.jpeg?biz_tag=pcweb_cover&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&sc=cover&se=false&x-expires=1996239600&x-signature=QCNywvpquxzT%2FlCUIPivcgbLnyE%3D"
//        )
//
//
//
//
//
//        mViewBinding.customProgressBar.setProgress(0, 2000)
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            mViewBinding.customProgressBar.setProgress(100, 2000)
//
//
//        }, 2000L)
//        // 示例：更新进度条进度
//        Handler(Looper.getMainLooper()).postDelayed({
//            mViewBinding.customProgressBar.setProgress(1000, 2000)
//        }, 4000L)
//        Handler(Looper.getMainLooper()).postDelayed({
//            mViewBinding.customProgressBar.setProgress(2000, 2000)
//        }, 6000L)
//        mViewBinding.viewTaskProgress.setListProgressCoin(
//            listOf(
//                ShortPlayYouXuanTask(mins = 1, coins = 88),
//                ShortPlayYouXuanTask(mins = 5, coins = 188),
//                ShortPlayYouXuanTask(mins = 15, coins = 288),
//                ShortPlayYouXuanTask(mins = 30, coins = 388),
//                ShortPlayYouXuanTask(mins = 60, coins = 588),
//                ShortPlayYouXuanTask(mins = 120, coins = 888),
//                ShortPlayYouXuanTask(mins = 150, coins = 988)
//
//            ), 0, 14
//        )
//    }
//
//
//    override fun initClicks() {
//    }
//
//
//    override fun onResume() {
//        super.onResume()
//        mViewBinding.rpvTask.resumePlay()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mViewBinding.rpvTask.pausePlay()
//    }
//}