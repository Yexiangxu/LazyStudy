package com.lazyxu.base.utils.permission

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 使用方式：
 * 请求单个权限（如 Android 13 通知权限）
 * PermissionHelper.requestPermission(
 *     this,
 *     Manifest.permission.POST_NOTIFICATIONS,
 *     onGranted = {
 *         // 权限通过，发送通知
 *         sendNotification(this)
 *     },
 *     onDenied = {
 *         Toast.makeText(this, "通知权限被拒绝", Toast.LENGTH_SHORT).show()
 *     }
 * )
 * 请求多个权限（如相机 + 存储）
 * PermissionHelper.requestPermissions(
 *     this,
 *     arrayOf(
 *         Manifest.permission.CAMERA,
 *         Manifest.permission.READ_EXTERNAL_STORAGE
 *     ),
 *     onAllGranted = {
 *         // 所有权限都通过
 *         openCamera()
 *     },
 *     onAnyDenied = {
 *         Toast.makeText(this, "请允许所有权限以继续使用", Toast.LENGTH_SHORT).show()
 *     }
 * )
 *
 */
object PermissionHelper {

    fun requestPermission(
        activity: ComponentActivity,
        permission: String,
        onGranted: () -> Unit,
        onDenied: (() -> Unit)? = null
    ) {
        val launcher = activity.activityResultRegistry.register(
            "request_permission_$permission${System.currentTimeMillis()}",
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                onGranted()
            } else {
                onDenied?.invoke()
            }
        }

        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                launcher.launch(permission)
                activity.lifecycle.removeObserver(this)
            }
        })
    }

    fun requestPermissions(
        activity: ComponentActivity,
        permissions: Array<String>,
        onAllGranted: () -> Unit,
        onAnyDenied: (() -> Unit)? = null
    ) {
        val launcher = activity.activityResultRegistry.register(
            "request_permissions_${System.currentTimeMillis()}",
            ActivityResultContracts.RequestMultiplePermissions()
        ) { resultMap ->
            val allGranted = resultMap.all { it.value }
            if (allGranted) {
                onAllGranted()
            } else {
                onAnyDenied?.invoke()
            }
        }

        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                launcher.launch(permissions)
                activity.lifecycle.removeObserver(this)
            }
        })
    }
}
