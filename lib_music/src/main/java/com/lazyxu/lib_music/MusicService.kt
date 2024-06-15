package com.lazyxu.lib_music

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

class MusicService:Service() {
    override fun onCreate() {
        super.onCreate()
        binder = MusicServiceBinder(this)

        initPlayerService()

    }
    private fun initPlayerService() {
        initTelephony()
    }
    var binder: MusicServiceBinder? = null
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
    /**
     * 初始化电话监听服务，电话响了要暂停
     */
    private var telephonyManager: TelephonyManager? = null
    private fun initTelephony() {
        if (telephonyManager == null) {
            telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager // 获取电话通讯服务
            telephonyManager?.listen(object : PhoneStateListener() {
                override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                    super.onCallStateChanged(state, phoneNumber)
                    when (state) {
                        TelephonyManager.CALL_STATE_OFFHOOK,
                        TelephonyManager.CALL_STATE_RINGING,
                        -> {
                            binder?.player?.pause()
                        }
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE) // 创建一个监听对象，监听电话状态改变事件
        }
    }
}