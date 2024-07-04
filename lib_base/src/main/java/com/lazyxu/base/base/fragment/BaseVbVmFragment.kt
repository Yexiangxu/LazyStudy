package com.lazyxu.base.base.fragment


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.lazyxu.base.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType


abstract class BaseVbVmFragment<VB : ViewBinding, VM : ViewModel> : BaseVbFragment<VB>() {

    lateinit var mViewModel: VM


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
        createObserver()
    }

    private fun initViewModel() {
        //actualTypeArguments[1]是通过反射获取 VM，因为在该类中 VM 是在第二个所以用[1]
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(argument[1].saveAsUnChecked())
    }

    abstract fun createObserver()
}