package com.lazyxu.mine.ui.postnews

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lazyxu.base.arouter.ARouterPath
import com.lazyxu.base.base.actvity.BaseVbVmActivity
import com.lazyxu.base.base.head.HeadToolbar
import com.lazyxu.base.ext.dp2px
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.AppToast
import com.lazyxu.base.utils.decoration.NormalItemDecoration
import com.lazyxu.mine.R
import com.lazyxu.mine.databinding.ActivityPostNewsBinding
import com.lazyxu.mine.utils.GlideEngine
import com.lazyxu.mine.utils.ImageFileCompressEngine
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnExternalPreviewEventListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.style.PictureSelectorStyle

@Route(path = ARouterPath.Mine.POSTNEWS)
class PostNewsActivity : BaseVbVmActivity<ActivityPostNewsBinding, PostNewsViewModel>() {
    override fun headToolbar() = HeadToolbar.Builder()
        .toolbarTitle(R.string.feedback)
        .build()

    private val viewModel by lazy {
        ViewModelProvider(this)[PostNewsViewModel::class.java]
    }
    private val maxImgSize = 6 // 最大选择图片数
    private var selectList: ArrayList<LocalMedia> = ArrayList()
    private var imgList = mutableListOf<String>()
    private val postNewsGridImgAdapter: PostNewsGridImgAdapter by lazy {
        PostNewsGridImgAdapter(maxImgSize, selectList) {
            setOnItemClick(it)
        }
    }

    override fun initView() {

        initRecyclerView()
    }


    override fun createObserver() {
//        viewModel.publishSubjectLiveData.observe(this, {
//            it?.let {
//                val intent = intent
////                intent.putExtra(Keys.CommunityMain.POSTNEW_KEY, it)
//                setResult(RESULT_OK, intent)
//                finish()
//            }
//        })
//        viewModel.uploadImgLiveData.observe(this, {
//            if (it.isNullOrEmpty()) {
//                AppToast.show("图片上传失败")
//                return@observe
//            }
//            imgList.add(it)
//            LogUtils.d(LogTag.PIC_SELECTOR, "imgList=${imgList}")
//            LogUtils.d(LogTag.PIC_SELECTOR, "data.size=${postNewsGridImgAdapter.data.size}")
//            if (imgList.size == postNewsGridImgAdapter.data.size) {
//                uploadImgs()
//            }
//        })
    }

    override fun initClicks() {
        mViewBinding.etPostnews.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                LogUtils.d(
                    LogTag.PIC_SELECTOR,
                    "beforeTextChanged s=$s,start=$start,count=$count,after=$after"
                )
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                LogUtils.d(
                    LogTag.PIC_SELECTOR,
                    "onTextChanged s=$s,start=$start,before=$before,count=$count"
                )
            }

            override fun afterTextChanged(s: Editable) {
                LogUtils.d(LogTag.PIC_SELECTOR, "afterTextChanged s=$s")
                "${s.length}/500".also { mViewBinding.tvInputNumb.text = it }
            }
        })
        mViewBinding.btnPost.setOnClickListener {
            if (mViewBinding.etPostnews.text.toString().trim().length < 3) {
                AppToast.show("至少要有三个字才可以发表哦")
                return@setOnClickListener
            }
            imgList.clear()
            if (postNewsGridImgAdapter.data.isEmpty()) {
                uploadImgs()
            } else {
//                postNewsGridImgAdapter.data.forEach {
//                    val name: RequestBody = RequestBody.create(
//                        MediaType.parse("multipart/form-data"),
//                        "${it.id}${System.currentTimeMillis()}"
//                    )
//                    val file = File(it.compressPath)
//                    val requestImgFile: RequestBody =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
//                    val requestImgPart: MultipartBody.Part =
//                        MultipartBody.Part.createFormData(
//                            "file",
//                            "${it.id}_${System.currentTimeMillis()}.jpg",
//                            requestImgFile
//                        )
//                    viewModel.uploadImg(name, requestImgPart)
//                }
            }
        }
    }

    private fun uploadImgs() {
//        val map: MutableMap<String, Any> = HashMap()
//        map["content"] = mViewBinding.etPostnews.text?.trim().toString()
//        map["img_list"] = imgList
//        val requestBody: RequestBody = RequestBody.create(
//            MediaType.parse("application/json; charset=utf-8"),
//            Gson().toJson(map)
//        )
//        viewModel.publishSubject(requestBody)
    }


    private fun initRecyclerView() {
        mViewBinding.recyclerImg.addItemDecoration(NormalItemDecoration(LinearLayoutManager.HORIZONTAL).apply {
            setBounds(left = 5.dp2px, right = 5.dp2px)
            removeStartEndMargin(true)
        })
        mViewBinding.recyclerImg.adapter = postNewsGridImgAdapter
    }

    private fun setOnItemClick(position: Int) {
        if (position == -1) {
            // 添加图片
            PictureSelector.create(this)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .setSelectorUIStyle(PictureSelectorStyle())
                .isPreviewFullScreenMode(true)
                .setMaxSelectNum(maxImgSize)
                .setCompressEngine(ImageFileCompressEngine())
                .setSelectedData(postNewsGridImgAdapter.data)
                .isPreviewZoomEffect(true)
                .setSelectionMode( SelectModeConfig.MULTIPLE)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>) {
                        postNewsGridImgAdapter.setList(result)
                    }

                    override fun onCancel() {
                    }
                })
        } else {
            PictureSelector.create(this)
                .openPreview()
                .setImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .setSelectorUIStyle(PictureSelectorStyle())
                .isPreviewFullScreenMode(true)
                .isPreviewZoomEffect(true,mViewBinding.recyclerImg)
                .setExternalPreviewEventListener(object : OnExternalPreviewEventListener {
                    override fun onPreviewDelete(position: Int) {
                        postNewsGridImgAdapter.delete(position)
                    }

                    override fun onLongPressDownload(context: Context, media: LocalMedia): Boolean {
                        return false
                    }
                })
                .startActivityPreview(position, true, postNewsGridImgAdapter.data)
        }
    }
}