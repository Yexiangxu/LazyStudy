package com.lazyxu.function.cache

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.os.StatFs
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.function.R
import com.lazyxu.function.adapter.MainAdapter
import com.lazyxu.function.databinding.FunctionActivityCacheBinding
import com.lazyxu.function.materialdesign.DragRecyclerViewActivity.Companion.PERMISSION_REQUEST_CODE
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


/**
 * User:Lazy_xu
 * Date:2024/03/19
 * Description:
 * FIXME
 */
@Route(path = ARouterPath.Function.CACHE)
class CacheActivity : BaseVbActivity<FunctionActivityCacheBinding>() {
    private lateinit var mList: MutableList<String>
    private lateinit var mAdapter: MainAdapter
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.res_cache)
        .build()


    override fun initView() {
        mList = mutableListOf(
            "内部存储 getDataDirectory",
            "内部存储 filesDir",
            "内部存储 cacheDir",
            "内部存储 getDir",
            "内部存储 openFileInput",
            "内部存储 openFileOutput",
            "查看内存和内外部存储空间大小",
            "动态申请权限及外部存储使用"
        )
        mAdapter = MainAdapter(this, mList)
        mViewBinding.rvMain.adapter = mAdapter
    }


    override fun initClicks() {
        mAdapter.setOnItemClickListener {
            when (it) {
                0 -> {
                    val dataDirectory = Environment.getDataDirectory()
                    Log.d(
                        "CacheTag",
                        "absolutePath=${dataDirectory.absolutePath},path=${dataDirectory.path}"
                    )
                }

                1 -> {
                    val filesDir = filesDir
                    Log.d("CacheTag", "absolutePath=${filesDir.absolutePath},path=${filesDir.path}")
                    val file = File(filesDir, "lazyxu_filesdir")
                    Log.d("CacheTag", "absolutePath=${file.absolutePath},path=${file.path}")
                    file.parentFile?.let { parent ->
                        if (!parent.exists()) {
                            parent.mkdirs()
                        }
                    }
                }

                2 -> {
                    val cacheDir = cacheDir
                    Log.d("CacheTag", "absolutePath=${cacheDir.absolutePath},path=${cacheDir.path}")
                }

                3 -> {
                    val getDir = getDir("customer", MODE_PRIVATE)
                    Log.d("CacheTag", "absolutePath=${getDir.absolutePath},path=${getDir.path}")
                }

                4 -> {
                    // 写入数据到文件
                    try {
                        val fos = openFileOutput("openFileExample.txt", MODE_PRIVATE)
                        fos.write("Open File Example lazyxu".toByteArray())
                        fos.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                5 -> {
                    // 读取文件内容
                    val stringBuilder = StringBuilder()
                    try {
                        val fis = openFileInput("openFileExample.txt")
                        val inputStreamReader = InputStreamReader(fis)
                        val bufferedReader = BufferedReader(inputStreamReader)
                        var text: String?
                        while (bufferedReader.readLine().also { text = it } != null) {
                            stringBuilder.append(text)
                        }
                        fis.close()
                        Log.d("CacheTag", "content=${stringBuilder.toString()}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                6 -> {
                    // 获取内部存储总的存储空间和剩余存储空间
                    val internalStorageDirectory = cacheDir // 内部存储目录
                    val internalStatFs = StatFs(internalStorageDirectory.path)
                    val internalBlockSize: Long = internalStatFs.blockSizeLong
                    val internalTotalBlocks: Long = internalStatFs.blockCountLong
                    val internalAvailableBlocks: Long = internalStatFs.availableBlocksLong
                    val internalTotalSpace: Long = internalBlockSize * internalTotalBlocks
                    val internalFreeSpace: Long = internalBlockSize * internalAvailableBlocks
                    // 转换为GB
                    val gb = 1024 * 1024 * 1024
                    Log.d("CacheTag", "内部存储总空间: ${internalTotalSpace / gb} GB")
                    Log.d("CacheTag", "内部存储剩余空间: ${internalFreeSpace / gb} GB")
                    // 获取外部存储总的存储空间和剩余存储空间
                    if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {//需要确保外部存储处于 MEDIA_MOUNTED 状态，表示它已经被挂载,可用
                        val externalStorageDirectory = externalCacheDir// 外部存储目录
                        val externalStatFs = StatFs(externalStorageDirectory!!.path)
                        val externalBlockSize: Long = externalStatFs.blockSizeLong
                        val externalTotalBlocks: Long = externalStatFs.blockCountLong
                        val externalAvailableBlocks: Long = externalStatFs.availableBlocksLong
                        val externalTotalSpace: Long = externalBlockSize * externalTotalBlocks
                        val externalFreeSpace: Long = externalBlockSize * externalAvailableBlocks
                        Log.d("CacheTag", "外部存储总空间: ${externalTotalSpace / gb} GB")
                        Log.d("CacheTag", "外部存储剩余空间: ${externalFreeSpace / gb} GB")
                    } else {
                        Log.d("CacheTag", "外部存储不可用")
                    }
                    val runtime = Runtime.getRuntime()
                    val maxMemory = runtime.maxMemory() // 最大内存
                    val totalMemory = runtime.totalMemory() // 当前已分配的内存
                    val freeMemory = runtime.freeMemory() // 当前可用的内存
                    Log.d("CacheTag", "最大内存: ${maxMemory / (1024 * 1024)} MB")
                    Log.d("CacheTag", "当前已分配的内存: ${totalMemory / (1024 * 1024)} MB")
                    Log.d("CacheTag", "当前可用的内存: ${freeMemory / (1024 * 1024)} MB")
                    val hasMemorySpace = freeMemory > 0
                    Log.d("CacheTag", "是否有可用的内存空间: $hasMemorySpace")
                }

                7 -> {
                    // 检查是否有外部存储权限
                    if (checkExternalStoragePermission()) {
                        // 如果有权限，就执行文件读写操作
                        performFileOperations()
                    } else {
                        // 如果没有权限，请求权限
                        requestExternalStoragePermission()
                    }
                }
            }
        }
    }

    private fun checkExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    private val fileName = "example.txt"
    private val data = "Hello, World!"
    private fun performFileOperations() {
        // 获取外部存储目录
        val externalStorageDirectory = getExternalFilesDir(null)

        // 如果外部存储目录可用，就在其中创建文件并写入数据
        externalStorageDirectory?.let { externalDir ->
            val file = File(externalDir, fileName)
            file.writeText(data)

            // 从外部存储中读取文件内容
            val fileContent = file.readText()
            Log.d("CacheTag", "fileContent=$fileContent")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 如果用户授予了权限，执行文件读写操作
                performFileOperations()
            } else {
                // 如果用户拒绝了权限，显示提示信息
                Toast.makeText(this, "外部存储权限被拒绝", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


