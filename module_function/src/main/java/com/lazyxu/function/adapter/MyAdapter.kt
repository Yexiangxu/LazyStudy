//package com.lazyxu.function.adapter
//
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.lazyxu.function.R
//
///**
// * User:Lazy_xu
// * Date:2024/02/26
// * Description:
// * FIXME
// */
//
//
//class MyAdapter(private val context: Context, private val dataList: MutableList<String>) :
//    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView: TextView = itemView.findViewById(R.id.textView)
//        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.function_item_delete, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = dataList[position]
//        holder.textView.text = data
//
//        holder.itemView.setOnTouchListener(object : OnSwipeTouchListener(context) {
//            override fun onSwipeLeft() {
//                super.onSwipeLeft()
//                holder.deleteButton.visibility = View.VISIBLE
//            }
//        })
//
//        holder.deleteButton.setOnClickListener {
//            dataList.removeAt(holder.adapterPosition)
//            notifyItemRemoved(holder.adapterPosition)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//}
