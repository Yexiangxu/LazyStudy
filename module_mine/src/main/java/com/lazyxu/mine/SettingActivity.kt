package com.lazyxu.mine

import android.content.Intent
import android.os.Build
import android.view.Gravity
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.constants.SpKey
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.utils.AudioPlayManager
import com.lazyxu.base.utils.DeviceUtil
import com.lazyxu.base.utils.SpUtils
import com.lazyxu.base.utils.VibrateUtils
import com.lazyxu.mine.databinding.ActivitySettingBinding


@Route(path = ARouterPath.Mine.SETTING)
class SettingActivity : BaseVbActivity<ActivitySettingBinding>() {

    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.settings)
        .build()

    override fun initView() {
        mViewBinding.scShake.isChecked = SpUtils.getBoolean(SpKey.OPEN_VIBRATE)
        mViewBinding.scClickSound.isChecked = SpUtils.getBoolean(SpKey.OPEN_CLICK_SOUND)
        mViewBinding.scOpenNight.isChecked = DeviceUtil.isDarkTheme(this)
    }


    override fun initClicks() {
        mViewBinding.scShake.setOnCheckedChangeListener { _, isChecked ->
            SpUtils.put(SpKey.OPEN_VIBRATE, isChecked)
            VibrateUtils.vibrate()
//            Snackbar
//                .make(mViewBinding.root, "震动已开启", Snackbar.LENGTH_SHORT)
//                .setAction("记住了") {
//                    Toast.makeText(this, "祝你一夜暴富！", Toast.LENGTH_LONG).show()
//                }
//                .show()


            val snackbar =
                Snackbar.make(mViewBinding.root, "Your message here", Snackbar.LENGTH_SHORT)
            val snackbarView = snackbar.view
            val textView =
                snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            if (textView != null) {
                textView.gravity = Gravity.CENTER_HORIZONTAL
            }
            snackbar.show()

        }
        mViewBinding.scClickSound.setOnCheckedChangeListener { _, isChecked ->
            SpUtils.put(SpKey.OPEN_CLICK_SOUND, isChecked)
            AudioPlayManager.instance.clickSound()
        }
        mViewBinding.scOpenNight.setOnCheckedChangeListener { _, isChecked ->
            if (DeviceUtil.isDarkTheme(this)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        mViewBinding.btnExitLogin.setOnClickListener(click)
    }

    private val click = OnClickListener {
        when (it.id) {
            R.id.btn_exit_login -> {
                val intent = Intent()
                intent.action = android.provider.Settings.ACTION_WIFI_SETTINGS
                startActivity(intent)
                Build.VERSION_CODES.Q
            }
        }
    }
}
