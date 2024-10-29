package com.lazyxu.mine.ui

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.snackbar.Snackbar
import com.lazyxu.base.arouter.ARouterHelper
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.constants.SpKey
import com.lazyxu.base.utils.AudioPlayManager
import com.lazyxu.base.utils.DeviceUtil
import com.lazyxu.base.utils.SpUtils
import com.lazyxu.base.utils.VibrateUtils
import com.lazyxu.base.utils.permission.RxPermissions
import com.lazyxu.mine.R
import com.lazyxu.mine.databinding.ActivitySettingBinding
import kotlinx.coroutines.launch
import java.io.File


@Route(path = ARouterPath.Mine.SETTING)
class SettingActivity : BaseVbActivity<ActivitySettingBinding>() {

    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.settings)
        .build()


    override fun initView() {
        registerActivityResult()
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
        mViewBinding.tvFeedback.setOnClickListener{
            ARouterHelper.goActivity(ARouterPath.Mine.POSTNEWS)
        }

        mViewBinding.btnExitLogin.setOnClickListener(click)
        mViewBinding.btnExitLogin.setOnClickListener {
            PhotoChoseDialogFragment(openCallback = {
                lifecycleScope.launch {
                    RxPermissions(activity = this@SettingActivity)
                        .request(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        .collect { granted ->
                            if (granted) {
                                openAlbum()
                            } else {
                            }
                        }
                }
            }).show(this)
        }
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

    var mActivityResultLauncherAlbum: ActivityResultLauncher<Intent>? = null//相册回调
    private fun registerActivityResult() {
        mActivityResultLauncherAlbum =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.data?.let {
                        val imagePath = getImagePathFromUri(it)
                        Log.d("TestTag", "imagePath=$imagePath")
//                        val bitmap=getBitmapFromPath(imagePath!!)
                        val bitmap = getBitmapFromUri(it)
                        Log.d("TestTag", "bitmap=$bitmap,width=${bitmap?.width},heigt=${bitmap?.height},densityDpi=${resources.displayMetrics.densityDpi}")
                    }
                }
            }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        val contentResolver = contentResolver
        return try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // 打开本地相册
    private fun openAlbum() {
        val intentAlbum = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mActivityResultLauncherAlbum?.launch(intentAlbum)
    }

    private fun getImagePathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        return cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (it.moveToFirst()) {
                val path = it.getString(columnIndex)
                Log.d("ImagePath", "Image path: $path")
                path
            } else {
                null
            }
        }
    }
    private fun getBitmapFromPath(path: String): Bitmap? {
        return try {
            val file = File(path)
            if (file.exists()) {
                BitmapFactory.decodeFile(file.absolutePath)
            } else {
                Log.d("ImagePath", "File does not exist at path: $path")
                null
            }
        } catch (e: Exception) {
            Log.d("ImagePath", "Error decoding file", e)
            null
        }
    }
    private fun decodeSampledBitmap(filePath: String?): Bitmap? {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        options.inSampleSize = 1
        options.outWidth
        options.inScaled = false
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

}
