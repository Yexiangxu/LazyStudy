package com.lazyxu.base.utils.permission


import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

class RxPermissions @JvmOverloads constructor(
    activity: FragmentActivity? = null,
    fragment: Fragment? = null
) {
    private val mRxPermissionsFragment: Lazy<RxPermissionsFragment> =
        if (activity != null) getLazySingleton(activity.supportFragmentManager)
        else getLazySingleton(fragment!!.childFragmentManager)

    private fun getLazySingleton(fragmentManager: FragmentManager): Lazy<RxPermissionsFragment> {
        return object : Lazy<RxPermissionsFragment> {
            private var rxPermissionsFragment: RxPermissionsFragment? = null

            @Synchronized
            override fun get(): RxPermissionsFragment {
                if (rxPermissionsFragment == null) {
                    rxPermissionsFragment = getRxPermissionsFragment(fragmentManager)
                }
                return rxPermissionsFragment!!
            }
        }
    }

    private fun getRxPermissionsFragment(fragmentManager: FragmentManager): RxPermissionsFragment {
        val rxPermissionsFragment = findRxPermissionsFragment(fragmentManager)
        return if (rxPermissionsFragment == null) {
            val newFragment = RxPermissionsFragment()
            fragmentManager.beginTransaction().add(newFragment, TAG).commitNow()
            newFragment
        } else {
            rxPermissionsFragment
        }
    }

    private fun findRxPermissionsFragment(fragmentManager: FragmentManager): RxPermissionsFragment? {
        return fragmentManager.findFragmentByTag(TAG) as? RxPermissionsFragment
    }

    fun request(vararg permissions: String): Flow<Boolean> = flow {
        val results = requestImplementation(*permissions).toList()
        val granted = results.all { it.granted }
        emit(granted)
    }

    private fun requestImplementation(vararg permissions: String): Flow<Permission> = flow {
        val list = mutableListOf<Flow<Permission>>()
        val unrequestedPermissions = mutableListOf<String>()
        permissions.forEach { permission ->
            mRxPermissionsFragment.get().log("Requesting permission $permission")
            when {
                isGranted(permission) -> list.add(flowOf(Permission(permission, true, false)))
                isRevoked(permission) -> list.add(flowOf(Permission(permission, false, false)))
                else -> {
                    var subject = mRxPermissionsFragment.get().getSubjectByPermission(permission)
                    if (subject == null) {
                        unrequestedPermissions.add(permission)
                        subject = MutableSharedFlow()
                        mRxPermissionsFragment.get().setSubjectForPermission(permission, subject)
                    }
                    list.add(subject)
                }
            }
        }
    }

    private fun isGranted(permission: String): Boolean {
        return !isMarshmallow() || mRxPermissionsFragment.get().isGranted(permission)
    }

    /**
     * 权限是否已被系统撤销
     */
    private fun isRevoked(permission: String): Boolean {
        return isMarshmallow() && mRxPermissionsFragment.get().isRevoked(permission)
    }

    fun setLogging(logging: Boolean) {
        mRxPermissionsFragment.get().setLogging(logging)
    }

    private fun isMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT >= 23
    }

    interface Lazy<V> {
        fun get(): V
    }

    companion object {
        val TAG: String = this::class.java.simpleName
    }
}
