package com.lazyxu.base.utils.permission

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.lazyxu.base.log.LogUtils
import kotlinx.coroutines.flow.MutableSharedFlow

internal class RxPermissionsFragment : Fragment() {
    private val mSubjects: MutableMap<String, MutableSharedFlow<Permission>> = HashMap()
    private var mLogging: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(permissions: Array<String>) {
        requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
    }


    //    @TargetApi(23)
//    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<String>, grantResults: IntArray ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == PERMISSIONS_REQUEST_CODE) {
//            val shouldShowRequestPermissionRationale = BooleanArray(permissions.size)
//
//            for (i in permissions.indices) {
//                shouldShowRequestPermissionRationale[i] =
//                    shouldShowRequestPermissionRationale(permissions[i])
//            }
//
//            onRequestPermissionsResult(
//                permissions,
//                grantResults,
//                shouldShowRequestPermissionRationale
//            )
//        }
//    }
    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != PERMISSIONS_REQUEST_CODE) return

        val shouldShowRequestPermissionRationale = permissions.map {
            shouldShowRequestPermissionRationale(it)
        }.toBooleanArray()

        onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale)
    }

    fun onRequestPermissionsResult(
        permissions: Array<String>,
        grantResults: IntArray,
        shouldShowRequestPermissionRationale: BooleanArray
    ) {
        for (i in permissions.indices) {
            log("onRequestPermissionsResult  ${permissions[i]}")
            val subject = mSubjects[permissions[i]]
            if (subject == null) {
                log("RxPermissions.onRequestPermissionsResult invoked but didn't find the corresponding permission request.")
                return
            }

            mSubjects.remove(permissions[i])
            val granted = grantResults[i] == 0
            subject.tryEmit(
                Permission(
                    permissions[i],
                    granted,
                    shouldShowRequestPermissionRationale[i]
                )
            )
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun isGranted(permission: String): Boolean {
        val activity = activity
            ?: throw IllegalStateException("This fragment must be attached to an activity.")
        return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isRevoked(permission: String): Boolean {
        val activity = activity
            ?: throw IllegalStateException("This fragment must be attached to an activity.")
        return activity.packageManager.isPermissionRevokedByPolicy(permission, activity.packageName)
    }

    fun setLogging(logging: Boolean) {
        mLogging = logging
    }

    fun getSubjectByPermission(@NonNull permission: String): MutableSharedFlow<Permission>? {
        return mSubjects[permission]
    }

    fun containsByPermission(@NonNull permission: String): Boolean {
        return mSubjects.containsKey(permission)
    }

    fun setSubjectForPermission(
        permission: String,
        subject: MutableSharedFlow<Permission>
    ) {
        mSubjects[permission] = subject
    }

    fun log(message: String) {
        if (mLogging) {
            LogUtils.d(RxPermissonUtils.TAG, message)
        }
    }


    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 42
    }
}
