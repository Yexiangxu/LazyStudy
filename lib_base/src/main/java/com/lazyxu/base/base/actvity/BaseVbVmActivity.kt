package com.lazyxu.base.base.actvity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * User:Lazy_xu
 * Date:2024/05/14
 * Description:
 * FIXME
 */
abstract class BaseVbVmActivity<VB : ViewBinding, VM : ViewModel> : BaseVbActivity<VB>() {
    lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel()//获取mViewModel要在初始化之前
        super.onCreate(savedInstanceState)
        createObserver()
    }


    abstract fun createObserver()

    private fun initViewModel() {
        //actualTypeArguments[1]是通过反射获取 VM，因为在该类中 VM 是在第二个所以用[1]
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this)[argument[1] as Class<VM>]
    }
}