package com.lazyxu.base.base.actvity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.R
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.interfaces.OnBackPressedListener
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.ActivitysManager


/**
 * User:Lazy_xu
 * Date:2024/05/14
 * Description:
 * FIXME
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d("${javaClass.simpleName} onCreate")
        ActivitysManager.addActivity(this)
        ARouter.getInstance().inject(this)
//        if (!isTaskRoot) { //防止首次安装按home键重新启动
//            val intent = intent
//            val action = intent.action
//            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action == Intent.ACTION_MAIN) {
//                finish()
//                return
//            }
//        }
        initContentView()
        setCommonTitle()
        initStatusbar()
        initView()
        initData()
        initClicks()
    }

    private fun setCommonTitle() {
        if (headToolbar() == null || headToolbar()?.toolbarTitle == null)
            return
        headToolbar()?.let { headToolbar ->
            val tbTitle = findViewById<Toolbar>(R.id.tb_title)
            if (tbTitle != null) {
                setSupportActionBar(tbTitle)//不设置使用menu无效，如果在单独页面设置需要重写setNavigationOnClickListener，否则setNavigationOnClickListener无效
                val drawable = tbTitle.overflowIcon
                drawable?.let {
                    DrawableCompat.setTint(it, ContextCompat.getColor(this, R.color.white))
                }
                when (headToolbar.toolbarTitle) {
                    is String -> tbTitle.title = headToolbar.toolbarTitle.toString()
                    is Int -> tbTitle.title = resources.getString(headToolbar.toolbarTitle as Int)
                }
                if (headToolbar.toolbarTitleColor != -1) {
                    tbTitle.setTitleTextColor(
                        ContextCompat.getColor(
                            this,
                            headToolbar.toolbarTitleColor
                        )
                    )
                }
                if (headToolbar.toolbarBgColor != -1) {
                    tbTitle.setBackgroundResource(headToolbar.toolbarBgColor)
                }
                if (headToolbar.backDrawable != -1) {
                    tbTitle.setNavigationIcon(headToolbar.backDrawable)
                }
                if (headToolbar.isHideBack) {
                    tbTitle.navigationIcon = null
                } else {
                    //部分情况该点击事件并不是返回而是别的操作，此处设置可重写
                    tbTitle.setNavigationOnClickListener {
                        onBackPressed()
                    }
                }
            }
        }
    }

    protected open fun initStatusbar(
        color: Int = headToolbar()?.statusBarColor ?: R.color.color_main_bg
    ) {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)//解决布局顶部和状态栏重叠问题
            .autoDarkModeEnable(true)//自动适配状态栏字体颜色
            .statusBarColor(color)
            .navigationBarColor(R.color.black)
            .hideBar(BarHide.FLAG_SHOW_BAR)
            .init()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        for (f in supportFragmentManager.fragments) {
            if (f is OnBackPressedListener && f.isVisible && f.onBackPressed()) {
                /*在Fragment中处理返回事件*/
                return
            }
        }
        if (headToolbar()?.backClick != null) {
            headToolbar()?.backClick?.invoke()
        } else {
            if (ActivitysManager.isActivityDestory(this)) {
                return
            }
            supportFinishAfterTransition()//If you use 'finish(); you will not get the animation effect
        }
    }

    /**
     * 用来统一设置标题导航栏状态
     */
    open fun headToolbar(): HeadToolbar? {
        return null
    }

    protected abstract fun initContentView()


    /**
     * 初始化操作，比如recyclerview等初始化
     */
    protected abstract fun initView()

    /**
     * 刚进页面需要网络请求等操作
     */
    protected open fun initData() {}

    /**
     * 处理所有点击事件
     */
    protected open fun initClicks() {}

    /**
     * 点击非edittext处软键盘消失
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v?.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        } // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0) //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return (event.x <= left || event.x >= right
                    || event.y <= top || event.y >= bottom)
        }
        return false
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.fontScale != 1f) {
            resources
        }
    }

    /**
     * 禁止改变字体大小
     */
    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }

    /**
     * 关闭软键盘
     */
    protected open fun closeSoftInput() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("${javaClass.simpleName} onStart")
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        LogUtils.d("${javaClass.simpleName} onRestoreInstanceState")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("${javaClass.simpleName} onResume")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d("${javaClass.simpleName} onRestart")
    }

    /**
     * 当前Activity的onPause方法执行结束后才会执行下一个Activity的onCreate 方法，所以在 onPause 方法中不适合做耗时较长的工作，这会影响到页面之间的跳 转效率
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d("${javaClass.simpleName} onPause")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        LogUtils.d("${javaClass.simpleName} onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("${javaClass.simpleName} onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("${javaClass.simpleName} onDestroy")
        ActivitysManager.removeActivity(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        LogUtils.d("${javaClass.simpleName} onNewIntent")

    }
}