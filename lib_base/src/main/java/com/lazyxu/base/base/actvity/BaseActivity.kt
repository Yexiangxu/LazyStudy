package com.lazyxu.base.base.actvity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.lazyxu.base.R
import com.lazyxu.base.base.head.HeadToolbar
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
        if (headToolbar() != null && headToolbar()?.toolbarTitle != null) {
            setCommonTitle()
        }
        ActivitysManager.addActivity(this)
        initStatusbar()
        initView()
        initData()
        initClicks()
    }

    private fun setCommonTitle() {
        val tbTitle = findViewById<Toolbar>(R.id.tb_title)
        headToolbar()?.let { headToolbar ->
            when (headToolbar.toolbarTitle) {
                is String -> {
                    tbTitle.title = headToolbar.toolbarTitle.toString()
                }

                is Int -> {
                    tbTitle.title = resources.getString(headToolbar.toolbarTitle as Int)
                }
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

    protected open fun initStatusbar() {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)//解决布局顶部和状态栏重叠问题
            .autoDarkModeEnable(true)//自动适配状态栏字体颜色
            .statusBarColor(headToolbar()?.statusBarColor ?: R.color.color_main_bg)
            .navigationBarColor(R.color.black)
            .hideBar(BarHide.FLAG_SHOW_BAR)
            .init()
    }

    override fun onBackPressed() {
        if (headToolbar()?.backClick != null) {
            headToolbar()?.backClick?.invoke()
        } else {
            if (ActivitysManager.isActivityDestory(this)) {
                return
            }
            finish()
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

    /**
     * 关闭软键盘
     */
    protected open fun closeSoftInput() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    /**
     * 当前Activity的onPause方法执行结束后才会执行下一个Activity的onCreate 方法，所以在 onPause 方法中不适合做耗时较长的工作，这会影响到页面之间的跳 转效率
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d("${javaClass.simpleName} onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("${javaClass.simpleName} onDestroy")
        ActivitysManager.removeActivity(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d("${javaClass.simpleName} onNewIntent")
    }
}