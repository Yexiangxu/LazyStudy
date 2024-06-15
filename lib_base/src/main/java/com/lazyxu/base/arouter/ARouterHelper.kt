package com.lazyxu.base.arouter

import android.app.Activity
import com.alibaba.android.arouter.launcher.ARouter
import com.lazyxu.base.R
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.utils.AppToast
import com.lazyxu.base.utils.NetUtils

object ARouterHelper {
    fun goActivity(path:String){
        ARouter.getInstance().build(path).navigation()
    }

    /**
     * 刚进入页面就需要网络请求，优先判断是否链接网络优化用户体验
     */
    fun goActivityNeedNet(path:String){
        if (NetUtils.isNetworkConnected(BaseApplication.INSTANCE)){
            ARouter.getInstance().build(path).navigation()
        }else{
            AppToast.show(R.string.net_disconnect)
        }
    }
    fun Activity.goActivityFinishCurrent(path:String){
        ARouter.getInstance().build(path).navigation()
        this.finish()
    }
}