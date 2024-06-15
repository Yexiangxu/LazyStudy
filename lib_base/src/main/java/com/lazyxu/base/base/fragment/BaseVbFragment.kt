package com.lazyxu.base.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lazyxu.base.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType

/**
 * User:Lazy_xu
 * Date:2024/05/14
 * Description:
 * FIXME
 */
abstract class BaseVbFragment<VB : ViewBinding> : BaseFragment() {

    lateinit var mViewBinding: VB
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        //actualTypeArguments[0]是通过反射获取第一个，如果 VB是在第二个就是用[1]
        val vbClass: Class<VB> =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].saveAsUnChecked()
        val method = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        mViewBinding = method.invoke(this, inflater, container, false)!!.saveAsUnChecked()
//        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)//需要在混淆文件中添加免混淆
//        mViewBinding = method.invoke(this, inflater)!!.saveAsUnChecked()
        return mViewBinding.root
    }

}