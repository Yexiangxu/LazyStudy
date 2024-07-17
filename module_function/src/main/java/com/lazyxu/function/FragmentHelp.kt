package com.lazyxu.function

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle



fun Fragment.finishActivity() {
    val currContext = context
    if (currContext != null && currContext is Activity) {
        currContext.finish()
    }
}

fun Fragment.findFragmentByTag(tag: String,fragmentManager: FragmentManager? = null) : Fragment?{
    val fm = fragmentManager?:childFragmentManager
    return fm.findFragmentByTag(tag)
}


fun <T : Fragment> Fragment.findFragmentByTagEnsureNotNll(tag: String,clz : String,fragmentManager: FragmentManager? = null) : T{
    val fm = fragmentManager?:childFragmentManager
    var fgm = fm.findFragmentByTag(tag)?: fm.fragmentFactory.instantiate(requireContext().classLoader,clz)
    return fgm as T
}

fun FragmentActivity.hideFragmentByTag(tag: String) {
    supportFragmentManager.findFragmentByTag(tag)?.let {
        supportFragmentManager.beginTransaction().hide(it).commitAllowingStateLoss()
    }
}

fun FragmentActivity.findFragmentByTag(tag: String) = supportFragmentManager.findFragmentByTag(tag)

fun Fragment.hideFragment(
    fm: FragmentManager? = null,
    fragment: Fragment? = null,
    leftAnimation: Int = 0,
    state : Lifecycle.State = Lifecycle.State.STARTED) {
    fragment?.let {
        if (it.isAdded && !it.isHidden) {
            fm?.beginTransaction()?.apply {
                hide(it)
                if(leftAnimation > 0){
                    setCustomAnimations(0, leftAnimation)
                }
                setMaxLifecycle(it, state)
                commitAllowingStateLoss()
            }
        }
    }
}

fun <T : Fragment> Fragment.showFragment(
    @IdRes containerViewId: Int
    , fragment: Fragment
    , args: Bundle? = null
    , tag: String? = null
    , fragmentManager: FragmentManager? = null
    , state : Lifecycle.State = Lifecycle.State.RESUMED
    , enterAni : Int = 0): Fragment? {
    val fm = fragmentManager?:childFragmentManager
    val transaction = fm.beginTransaction()
    if (fragment != null) {
        if (!fragment.isAdded) {
            args?.let {
                fragment.arguments = it
            }
            transaction.replace(containerViewId, fragment, tag)
            transaction.setMaxLifecycle(fragment,state)
            if(enterAni > 0){
                transaction.setCustomAnimations(enterAni,0)
            }
            transaction.commitAllowingStateLoss()

        } else if (fragment.isHidden) {
            args?.let {
                fragment.arguments = it
            }
            transaction.show(fragment)
            if(enterAni > 0){
                transaction.setCustomAnimations(enterAni,0)
            }
            transaction.setMaxLifecycle(fragment,state)
            transaction.commitAllowingStateLoss()
        }
    }
    return fragment
}

fun FragmentActivity.showFragment(
        @IdRes containerViewId: Int
        , fragment: Fragment
        , args: Bundle? = null
        , tag: String? = null
        , fragmentManager: FragmentManager? = null
        , state : Lifecycle.State = Lifecycle.State.RESUMED
): Fragment? {
    val fm = fragmentManager?:supportFragmentManager
    val transaction = fm.beginTransaction()
    if (fragment != null) {
        if (!fragment.isAdded) {
            args?.let {
                fragment.arguments = it
            }
            transaction.replace(containerViewId, fragment, tag)
            transaction.setMaxLifecycle(fragment,state)
            transaction.commitAllowingStateLoss()

        } else if (fragment.isHidden) {
            args?.let {
                fragment.arguments = it
            }
            transaction.show(fragment)
            transaction.setMaxLifecycle(fragment,state)
            transaction.commitAllowingStateLoss()
        }
    }
    return fragment
}


inline fun Fragment.requireParentFragmentManager(): FragmentManager{
    var fm = parentFragment?.requireFragmentManager()
    if(fm == null){
        fm = requireActivity().supportFragmentManager
    }
    return fm
}
