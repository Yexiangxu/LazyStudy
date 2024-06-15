package com.lazyxu.search.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEachIndexed
import com.google.android.material.chip.Chip
import com.lazyxu.search.R
import com.lazyxu.search.databinding.LayoutItemSearchHistoryBinding
import com.lazyxu.search.databinding.LayoutSearchHistoryBinding

/**
 * User:Lazy_xu
 * Date:2024/06/11
 * Description: @JvmOverloads 一定要有
 * FIXME
 */
class SearchHistoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var mViewBinding: LayoutSearchHistoryBinding
    private val mKeyWords = mutableListOf<String>()

    init {
        mViewBinding = LayoutSearchHistoryBinding.inflate(LayoutInflater.from(context), this, true)
        val array = context.obtainStyledAttributes(attrs, R.styleable.SearchHistoryView)
        val title = array.getString(R.styleable.SearchHistoryView_title)
        if (!title.isNullOrEmpty()) {
            mViewBinding.tvTitle.text = title
        }
        val drawSearchEnd = array.getDrawable(R.styleable.SearchHistoryView_draw_search_end)
        if (drawSearchEnd != null) {
            mViewBinding.ivDrawEnd.setImageDrawable(drawSearchEnd)
        }
        array.recycle()
    }

    /**
     * 设置数据
     */
    fun setHistoryData(histories: List<String>) {
        if (histories.isNullOrEmpty()) {
            return
        }
        mKeyWords.clear()
        mViewBinding.cgSearch.removeAllViews()
        mKeyWords.addAll(histories)
        histories.forEachIndexed { index, item ->
            val chipItem = createChipItem()
            mViewBinding.cgSearch.addView(chipItem)
            chipItem.text = item
        }
    }

    /**
     * 创建Chip
     */
    private fun createChipItem(): Chip {
        val chipItem: Chip =
            LayoutItemSearchHistoryBinding.inflate(
                LayoutInflater.from(context),
                mViewBinding.cgSearch,
                false
            ).root

        chipItem.isChecked = false //刚创建的时候是没有选中的
        chipItem.isCheckable = true //还需要去改变每个chip的id，这个id在布局文件的时候是相同的，但是我们添加到chipGroup的时候是不同的
        chipItem.id = mViewBinding.cgSearch.childCount ?: 0
//        chipItem.tag = mViewBinding.cgSearch.childCount ?: 0
//        chipItem.setOnClickListener {}
        return chipItem
    }

    /**
     * 需要暴露方法，点击item的时候把事件暴露出去
     */
    fun setOnCheckChangeListener(callBack: (String) -> Unit) {
        mViewBinding.cgSearch.setOnCheckedChangeListener { group, checkedId ->
            mViewBinding.cgSearch.forEachIndexed { index, view ->
                if (mViewBinding.cgSearch.getChildAt(index)?.id == checkedId) {
                    callBack.invoke(mKeyWords[index])
                    return@setOnCheckedChangeListener
                }
            }
        }
    }

    /**
     * 按钮点击(删除或刷新)
     */
    fun setOnDrawEndClickListener(callBack: () -> Unit) {
        mViewBinding.ivDrawEnd.setOnClickListener { callBack.invoke() }
    }
}