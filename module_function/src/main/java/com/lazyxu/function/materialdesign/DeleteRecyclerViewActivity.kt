package com.lazyxu.function.materialdesign

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.base.actvity.BaseVbActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.function.R
import com.lazyxu.function.databinding.FunctionActivityDeleteRecyclerviewBinding
import com.lazyxu.function.utils.GridSpaceItemDecoration


@Route(path = ARouterPath.Function.DELETEECYCLERVIEW)
class DeleteRecyclerViewActivity : BaseVbActivity<FunctionActivityDeleteRecyclerviewBinding>() {


    private lateinit var mAdapter: MyAdapter
    private lateinit var mGridItemDecoration: GridSpaceItemDecoration


    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.function_dragrecyclerview)
        .build()

    override fun initView() {
        initRecycleView()
    }


    override fun initClicks() {
    }


    private fun initRecycleView() {
        val list = getDatas()
        mViewBinding.rvDelete.layoutManager = LinearLayoutManager(this)

        mAdapter = MyAdapter(this)
        mViewBinding.rvDelete.adapter = mAdapter
//
//        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val position = viewHolder.adapterPosition
//                list.removeAt(position)
//                mAdapter.notifyItemRemoved(position)
//            }
//        }
//
//        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//        itemTouchHelper.attachToRecyclerView( mViewBinding.rvDelete)


        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback())
        itemTouchHelper.attachToRecyclerView(mViewBinding.rvDelete)
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


    private inner class MyAdapter(val context: Context) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        val items = mutableListOf<String>()

        fun setData(data: List<String>) {
            items.clear()
            items.addAll(data)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val button = Button(context)
            return ViewHolder(button)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.button.text = items[position]
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class ViewHolder(val button: Button) : RecyclerView.ViewHolder(button)
    }

    private inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        private val deleteButtonWidth = 200f // Width of delete button

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            mAdapter.notifyItemRemoved(position)
            mAdapter.setData(mAdapter.items.filterIndexed { index, _ -> index != position })
            Toast.makeText(this@DeleteRecyclerViewActivity, "Item removed", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView

            val deleteButtonBounds = RectF(
                itemView.right + dX - deleteButtonWidth,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )

            val paint = Paint().apply {
                color = Color.RED
            }

            c.drawRect(deleteButtonBounds, paint)

            val deleteText = "Delete"
            val textPaint = Paint().apply {
                color = Color.WHITE
                textSize = 50f
            }
            c.drawText(
                deleteText,
                deleteButtonBounds.centerX() - textPaint.measureText(deleteText) / 2,
                deleteButtonBounds.centerY() + textPaint.textSize / 2,
                textPaint
            )

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        override fun getSwipeDirs(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return super.getSwipeDirs(recyclerView, viewHolder)
        }
    }
}
