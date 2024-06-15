package com.lazyxu.base.base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.lazyxu.base.utils.ActivityUtils
import com.lazyxu.base.utils.MainLooper


open class BaseVbFastDialogFragment<VB : ViewBinding> : DialogFragment() {
    private lateinit var mDialogCallback: DialogCallback<VB>//必须要有
    lateinit var mViewBinding: VB
    private var mActivity: FragmentActivity? = null
    private lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun getTheme(): Int {
        mDialogCallback.bindTheme().let {
            if (it != View.NO_ID) {
                return it
            }
        }
        return super.getTheme()
    }

  

    private fun show(tag: String?) {
        MainLooper.instance.runOnUiThread {
            if (ActivityUtils.isActivityAlive(mActivity)) {
                mActivity?.supportFragmentManager?.let {
                    val prev: Fragment? = it.findFragmentByTag(tag)
                    if (prev != null) {
                        it.beginTransaction().remove(prev)
                    }
                    super@BaseVbFastDialogFragment.show(it, tag)
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mViewBinding = mDialogCallback.getViewBinding(inflater, container)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.setCanceledOnTouchOutside(true)
        mDialogCallback.initView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        val lp = window?.attributes
        lp?.gravity = mDialogCallback.gravityType()
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = lp
        return dialog
    }


    fun init(
        context: FragmentActivity?,
        dialogCallback: DialogCallback<VB>,
    ): BaseVbFastDialogFragment<VB> {
        mActivity = context
        mDialogCallback = dialogCallback
        return this
    }

    fun show() {
        show(javaClass.simpleName)
    }
}