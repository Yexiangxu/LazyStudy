package com.lazyxu.lazystudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazyxu.base.utils.ActivitysManager

/**
 * User:Lazy_xu
 * Date:2024/06/21
 * Description: 微信分享唤起
 * FIXME
 */
class WakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wakeUpApp(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        wakeUpApp(intent)
    }

    private fun wakeUpApp(intent: Intent?) {
        val wakedata = intent?.data
        if (isRecognizable(wakedata)) {//从短剧分享唤起的
            val id = wakedata?.getQueryParameter("video_id")
            if (ActivitysManager.getActivity(MainActivity::class.java) != null) {
//                WakeUpEvents.postValue(id)//如果主页面打开了直接跳转播放页
            } else {
//                WakeUpEvents.postValue(id)//如果主页面没打开及没启动过先进启动页再播放
                startActivity(Intent(this, SplashActivity::class.java))
            }
        } else {//从别的唤起的直接打开启动页
            startActivity(Intent(this, SplashActivity::class.java))
        }
        finish()
    }

    private fun isRecognizable(uri: Uri?): Boolean {
        return if (uri != null) {
            val url: String = uri.toString()
            return url.startsWith("lazyxu://openvideo")
        } else {
            false
        }
    }
}