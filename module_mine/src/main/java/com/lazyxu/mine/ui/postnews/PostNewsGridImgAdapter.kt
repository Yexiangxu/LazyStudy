package com.lazyxu.mine.ui.postnews

import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.lazyxu.base.ext.gone
import com.lazyxu.base.ext.loadRound
import com.lazyxu.base.ext.visible
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.AppToast
import com.lazyxu.mine.R
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.utils.ToastUtils
import java.io.File


class PostNewsGridImgAdapter(
    private val selectMax: Int = 6,
    var data: ArrayList<LocalMedia>,
    private val itemCallback: (Int) -> Unit
) :
    RecyclerView.Adapter<PostNewsGridImgAdapter.ViewHolder>() {
    companion object {
        const val TYPE_ADD = 1
        const val TYPE_PICTURE = 2
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivItemPic: ImageView = view.findViewById(R.id.ivItemPic)
        var ivItemDelete: ImageView = view.findViewById(R.id.ivItemDelete)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_post_news, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ADD) {
            holder.ivItemDelete.gone()
            holder.ivItemPic.setImageResource(R.drawable.svg_postnews_add)
            holder.ivItemPic.setOnClickListener { itemCallback.invoke(-1) }
        } else {
            holder.ivItemDelete.visible()
            holder.ivItemDelete.setOnClickListener { delete(holder.layoutPosition) }
            val item = data[position]
            // 拿到本地图片或其他文件
            if (TextUtils.isEmpty(item.path)) {
                return
            }
            // 暂只处理图片
            if (item.chooseModel != SelectMimeType.ofImage()) {
                AppToast.show("暂时只能添加图片哦")
                return
            }

            // 图片路径
            val path: String = if (item.isCut && !item.isCompressed) {
                // 裁剪过
                item.cutPath
            } else if (item.isCut || item.isCompressed) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                item.compressPath
            } else {
                // 原图
                item.path
            }
            LogUtils.d(LogTag.PIC_SELECTOR,"原图地址:: ${item.path}")
            if (item.isCut) {
                LogUtils.d(LogTag.PIC_SELECTOR,"裁剪地址:: ${item.cutPath}")
            }
            if (item.isCompressed) {
                LogUtils.d(LogTag.PIC_SELECTOR,"压缩地址:: ${item.compressPath}")
                LogUtils.d(LogTag.PIC_SELECTOR,"压缩后文件大小:: ${File(item.compressPath).length() / 1024}k")
            }
            if (item.isOriginal) {
                LogUtils.d(LogTag.PIC_SELECTOR,"已开启原图功能,地址:: ${item.originalPath}")
            }
            holder.ivItemPic.loadRound(
                if (PictureMimeType.isContent(path) && !item.isCut && !item.isCompressed) Uri.parse(
                    path
                ) else path
            )
            holder.ivItemPic.setOnClickListener {
                itemCallback.invoke(holder.layoutPosition)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (isShowAddItem(position)) {
            TYPE_ADD
        } else {
            TYPE_PICTURE
        }
    }

    /**
     * 是否显示添加item的按钮
     * 在未达到item限额时,默认显示添加item的按钮
     */
    private fun isShowAddItem(position: Int): Boolean = position == data.size

    /**
     * 设置add图标
     */

    override fun getItemCount(): Int {
        return if (data.size < selectMax) {
            data.size + 1
        } else {
            data.size
        }
    }

    /**
     * 删除
     */
    fun delete(position: Int) {
        try {
            if (position != RecyclerView.NO_POSITION && data.size > position) {
                data.removeAt(position)
                notifyItemRemoved(position)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun setList(result: ArrayList<LocalMedia>) {
        data=result
        notifyDataSetChanged()
    }
}