//package com.lazyxu.lib_music
//
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothHeadset
//import android.bluetooth.BluetoothProfile
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.media.AudioManager
//
///**
// * 耳机拔出广播接收器
// */
//private class BecomingNoisyReceiver(val binder: MusicServiceBinder?) : BroadcastReceiver() {
//    private val bluetoothAdapter: BluetoothAdapter by lazy {
//        BluetoothAdapter.getDefaultAdapter()
//    }
//    private val intentFilter: IntentFilter by lazy {
//        IntentFilter().apply {
//            addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)  //有线耳机拔出变化
//            addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED) //蓝牙耳机连接变化
//        }
//    }
//
//    private var registered = false
//
//    fun register(context: Context) {
//        if (!registered) {
//            context.registerReceiver(this, intentFilter)
//            registered = true
//        }
//    }
//
//    fun unregister(context: Context) {
//        if (registered) {
//            context.unregisterReceiver(this)
//            registered = false
//        }
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        //当前是正在运行的时候才能通过媒体按键来操作音频
//        val isPlaying = binder?.player?.isPlaying() ?: false
//        when (intent?.action) {
//            BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED -> {
//                LogUtils.d("蓝牙耳机插拔状态改变")
//                val state = bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)
//                if (BluetoothProfile.STATE_DISCONNECTED == state && isPlaying) {
//                    //蓝牙耳机断开连接 同时当前音乐正在播放 则将其暂停
//                    binder?.player?.pause()
//                }
//            }
//
//            AudioManager.ACTION_AUDIO_BECOMING_NOISY -> {
//                LogUtils.d("有线耳机插拔状态改变")
//                if (isPlaying) {
//                    //有线耳机断开连接 同时当前音乐正在播放 则将其暂停
//                    binder?.player?.pause()
//                }
//            }
//        }
//    }
//}