package com.lazyxu.function.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.lazyxu.function.R

/**
 * User:Lazy_xu
 * Date:2024/02/21
 * Description:
 * FIXME
 */
class MainAdapter(private val context: Context,private val list:List<String>):RecyclerView.Adapter<MainAdapter.ViewHolder>() {
   private var itemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.function_item_main, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTextView.text= list[position]
        holder.itemTextView.tag=position
        holder.itemTextView.setOnClickListener {
            itemClickListener?.invoke(position)
        }
    }
    fun setOnItemClickListener(clicklistener: ((Int) -> Unit)){
        itemClickListener=clicklistener
    }
    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var itemTextView: AppCompatTextView =itemView.findViewById(R.id.item_textView)

    }
}
//class MainAdapter(private val context: Context,private val list:List<String>):RecyclerView.Adapter<MainAdapter.ViewHolder>() {
//    private var itemClickListener: ((Int) -> Unit)? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.function_item_main, parent, false))
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemTextView.text= list[position]
//        holder.itemTextView.tag=position
//        holder.itemTextView.setOnClickListener {
//            itemClickListener?.invoke(position)
//        }
//    }
//    fun setOnItemClickListener(clicklistener: ((Int) -> Unit)){
//        itemClickListener=clicklistener
//    }
//    inner class ViewHolder(functionItemMainBinding: FunctionItemMainBinding) :RecyclerView.ViewHolder(functionItemMainBinding.root){}
//}