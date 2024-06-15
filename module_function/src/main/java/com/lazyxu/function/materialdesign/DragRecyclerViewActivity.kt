package com.lazyxu.function.materialdesign

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.function.R
import com.lazyxu.function.adapter.DragAdapter
import com.lazyxu.function.databinding.FunctionActivityDragRecyclerviewBinding
import com.lazyxu.function.utils.DragCallBack
import com.lazyxu.function.utils.GridSpaceItemDecoration


@Route(path = ARouterPath.Function.DRAGRECYCLERVIEW)
class DragRecyclerViewActivity : BaseVbActivity<FunctionActivityDragRecyclerviewBinding>() {

    private val tag = "DragRecyclerViewActivity"

    companion object {
        private var SPAN_COUNT = 4

        const val PERMISSION_REQUEST_CODE = 1001

    }

    private lateinit var mAdapter: DragAdapter
    private lateinit var mGridItemDecoration: GridSpaceItemDecoration


    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.function_dragrecyclerview)
        .build()

    override fun initView() {
        initRecycleView()
        setListener()
    }


    override fun initClicks() {
    }


    private fun initRecycleView() {
        mViewBinding.recycleView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        if (!::mGridItemDecoration.isInitialized) {
            mGridItemDecoration = GridSpaceItemDecoration(SPAN_COUNT)
            mViewBinding.recycleView.addItemDecoration(mGridItemDecoration, 0)
        }
        val list = getDatas()
        mAdapter = DragAdapter(this, list)
        mViewBinding.recycleView.adapter = mAdapter

        // 设置拖拽/滑动
        val dragCallBack = DragCallBack(mAdapter, list)
        val itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper.attachToRecyclerView(mViewBinding.recycleView)

        mAdapter.setOnItemClickListener(object : DragAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@DragRecyclerViewActivity,
                    dragCallBack.getData()[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onItemLongClick(holder: DragAdapter.ViewHolder) {
                if (holder.layoutPosition != mAdapter.fixedPosition) {
                    itemTouchHelper.startDrag(holder)
                }
            }
        })
    }

    private fun setListener() {
        mViewBinding.tvSwitch.setOnClickListener {
            when (mViewBinding.recycleView.layoutManager) {
                is GridLayoutManager -> {
                    mViewBinding.recycleView.layoutManager = LinearLayoutManager(this)
                }

                else -> {
                    mViewBinding.recycleView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
                }
            }
        }

        mAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                Log.i(
                    tag,
                    "onItemRangeMoved,fromPosition=$fromPosition, toPosition=$toPosition, itemCount=$itemCount"
                )
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                Log.i(tag, "onItemRangeRemoved")
            }

            override fun onChanged() {
                super.onChanged()
                Log.i(tag, "onChanged")
            }
        })
    }

    private fun getDatas(): MutableList<String> {
        return mutableListOf(
            "推荐",
            "Android",
            "iOS",
            "前端",
            "后端",
            "音视频",
            "大数据",
            "人工智能",
            "云原生",
            "运维",
            "算法",
            "代码人生"
        )
    }
}