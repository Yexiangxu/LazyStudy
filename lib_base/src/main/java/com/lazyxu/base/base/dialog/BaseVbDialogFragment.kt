package com.lazyxu.base.base.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.lazyxu.base.R
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.ActivityUtils
import com.lazyxu.base.utils.MainLooper
import java.lang.reflect.ParameterizedType


abstract class BaseVbDialogFragment<VB : ViewBinding> : AppCompatDialogFragment() {
    protected var mActivity: FragmentActivity? = null
    lateinit var mViewBinding: VB
    open fun gravity(): Int = Gravity.CENTER
    abstract fun initViews()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onCreateView")
        val vbClass: Class<VB> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        mViewBinding = method.invoke(this, inflater, container, false) as VB
        return mViewBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onViewCreated")
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setCancelable(false)
        initViews()
    }

    private fun show(tag: String?) {
        MainLooper.instance.runOnUiThread {
            if (ActivityUtils.isActivityAlive(mActivity)) {
                mActivity?.supportFragmentManager?.let {
                    val prev: Fragment? = it.findFragmentByTag(tag)
                    if (prev != null) {
                        it.beginTransaction().remove(prev).commitAllowingStateLoss()
                    }
                    super@BaseVbDialogFragment.show(it, tag)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //必须要这里设置style()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onCreate")
        setStyle(
            STYLE_NORMAL, when (gravity()) {
                Gravity.BOTTOM -> R.style.DialogBottomStyle
                else -> R.style.DialogCommonStyle
            }
        )
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onStart")
        val window = dialog?.window
        if (window != null) {
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window.setGravity(gravity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onDestroy")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onResume")

    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onPause")
    }


    override fun onStop() {
        super.onStop()
        LogUtils.d("DialogFragmentLifecycleTag", "${this.javaClass.simpleName} onStop")
    }


    fun show(context: FragmentActivity?) {
        mActivity = context
        show(javaClass.simpleName)
    }
}