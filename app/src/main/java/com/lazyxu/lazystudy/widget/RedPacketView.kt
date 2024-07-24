package com.lazyxu.lazystudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.lazyxu.base.ext.gone
import com.lazyxu.base.log.LogUtils
import com.lazyxu.lazystudy.databinding.ViewRedpacketBinding

class RedPacketView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mViewBinding: ViewRedpacketBinding

    init {
        mViewBinding = ViewRedpacketBinding.inflate(LayoutInflater.from(context), this, true)
        mViewBinding.ivCloseRedpacket.setOnClickListener {
            mViewBinding.clRedpacket.gone()
            pausePlay()
        }
    }

    companion object {
        private var count = 0 // 进度条更新次数
        private var cirCount = 0 // 转圈满了的次数
    }

    private var isPlaying = false // 是否正在播放视频中


    fun resumePlay() {
        LogUtils.d("RedPacketTag", "resumePlay")
        if (isPlaying) return
        isPlaying = true
        mViewBinding.cprRedpacket.post(task)
    }

    fun pausePlay() {
        LogUtils.d("RedPacketTag", "pausePlay")
        if (!isPlaying) return
        isPlaying = false
        mViewBinding.cprRedpacket.removeCallbacks(task)
    }
    var finishCallback: (() -> Unit)? = null
    fun setProgressBarFinishListener(finishCallback: (() -> Unit)? = null) {
        this.finishCallback=finishCallback
    }


    private val circleInterval = 60f // 转圈红包转一圈的时间,单位秒,默认30秒
    private val onceProgress = 100f / circleInterval
    private var task = object : Runnable {
        override fun run() {
            LogUtils.d("RedPacketTag", "count=$count,cirCount=$cirCount")
            count++
            val progress = count * onceProgress
            mViewBinding.cprRedpacket.setProgress(progress, true)
            mViewBinding.cprRedpacket.postDelayed(this, 1000L)
            if (progress >= 100) {
                finishCallback?.invoke()
                count = 0//重新开始计时
                cirCount++
                //开始得金币的动画并且调用接口
            }
        }
    }
}