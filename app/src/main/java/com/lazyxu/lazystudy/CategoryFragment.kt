package com.lazyxu.lazystudy

import android.widget.SeekBar
import com.lazyxu.base.base.fragment.BaseVbVmFragment
import com.lazyxu.lazystudy.databinding.FragmentCategoryBinding


class CategoryFragment : BaseVbVmFragment<FragmentCategoryBinding, HomeViewModel>() {

    override fun createObserver() {

    }

    override fun initData() {
    }

    override fun initView() {
        mViewBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 更新 TextView 的文本内容和位置
                updateThumbTextView(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateThumbTextView(progress: Int) {
        mViewBinding.thumbTextView.text = "$progress%"
        // 根据 SeekBar 的进度更新 TextView 的位置
        val thumbPos =
            (mViewBinding.seekBar.width - mViewBinding.seekBar.paddingLeft - mViewBinding.seekBar.paddingRight) * mViewBinding.seekBar.progress / mViewBinding.seekBar.max
        mViewBinding.thumbTextView.x =
            mViewBinding.seekBar.paddingLeft.toFloat() + thumbPos.toFloat() - mViewBinding.thumbTextView.width / 2
    }
}