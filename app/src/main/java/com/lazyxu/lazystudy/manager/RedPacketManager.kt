package com.lazyxu.lazystudy.manager

import com.lazyxu.base.log.LogUtils
import com.lazyxu.lazystudy.widget.CircularProgressView

object RedPacketManager {
    private var circularProgressView: CircularProgressView? = null
    private var isPlaying = false // 是否正在播放视频中

    fun init(circularProgressView: CircularProgressView) {
        this.circularProgressView = circularProgressView
    }

    fun resumePlay() {
        LogUtils.d("RedPacketTag", "resumePlay")
        if (isPlaying) return
        isPlaying = true
        circularProgressView?.post(task)
    }

    fun pausePlay() {
        LogUtils.d("RedPacketTag", "pausePlay")
        if (!isPlaying) return
        isPlaying = false
        circularProgressView?.removeCallbacks(task)
    }

    private var count = 0 // 进度条更新次数
    private var cirCount = 0 // 转圈满了的次数
    private val circleInterval = 60f // 转圈红包转一圈的时间,单位秒,默认30秒
    private val onceProgress = 100f / circleInterval
    private var task = object : Runnable {
        override fun run() {
            count++;
            val progress = count * onceProgress
            circularProgressView?.setProgress(progress,true)
            circularProgressView?.postDelayed(this, 1000L)
            if (progress >= 100) {
                count = 0//重新开始计时
                cirCount++
                //开始得金币的动画并且调用接口
            }
        }
    }
}