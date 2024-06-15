//package com.lazyxu.lazystudy
//
//import android.graphics.Rect
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.fragment_homepager.*
//
//class HomePagerFragment : Fragment() {
//    companion object {
//        fun getInstance(type: Int): Fragment {
//            return HomePagerFragment().apply {
//                arguments = Bundle().apply {
//                    putInt("type", type)
//                }
//            }
//        }
//    }
//
//    private val images = mutableListOf(
//            R.drawable.default_home1,
//            R.drawable.default_home3,
//            R.drawable.default_home4,
//            R.drawable.default_home1)
//
//    private val homePagerAdapter by lazy {
//        HomeTopAdapter()
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_homepager, container, false)
//    }
//
//    var itemHeight: Int? = 0
//
//    private val stragmanager = LinearLayoutManager(context)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        rvHomePager.apply {
//            layoutManager = stragmanager
//            adapter = homePagerAdapter
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//
//
//                        val firstShowPosition = stragmanager.findFirstVisibleItemPosition()
//                        val lastShowPosition = stragmanager.findLastVisibleItemPosition()
//                        Log.i("homepagetest", "firstposition=${firstShowPosition},lastpistion=${lastShowPosition}")
//
//                        if (itemHeight == 0) {
//                            itemHeight = stragmanager.findViewByPosition(firstShowPosition)?.height
//                        }
//                        val firstview = stragmanager.findViewByPosition(firstShowPosition)
//                        val lastview = stragmanager.findViewByPosition(lastShowPosition)
//                        if ((firstview?.bottom?.minus(rvHomePager.top))!! > (itemHeight?.div(2)!!)) {
//                            Log.i("homepagetest", "第一个item超过一半")
//                        } else {
//                            Log.i("homepagetest", "第一个item不足一半")
//                        }
//                        if ((lastview?.top?.let { rvHomePager?.bottom?.minus(it) })!! > (itemHeight?.div(2)!!)) {
//                            Log.i("homepagetest", "最后一个item超过一半")
//                        } else {
//                            Log.i("homepagetest", "最后一个item不足一半")
//                        }
//                        Log.i("homepagetest", "firstCompletelyVisibleposition=${stragmanager.findFirstCompletelyVisibleItemPosition()},lastCompletelyVisiblepoistion=${stragmanager.findLastCompletelyVisibleItemPosition()}")
//                        Log.i("homepagetest", "rvHomePager.top=${rvHomePager.top},rvHomePager.bottom${rvHomePager.bottom}")
//                        Log.i("homepagetest", "isVisibleLocal=${isVisibleLocal(rvHomePager)}")
//                    }
//                }
//            })
//
//
//        }
//        homePagerAdapter.setNewInstance(images)
//        homePagerAdapter.setOnItemClickListener { adapter, view, position ->
//            Toast.makeText(activity, "点击了$position", Toast.LENGTH_SHORT).show()
//        }
//        Log.i("homepagetest", "isVisibleLocal=${isVisibleLocal(rvHomePager)}")
//
//    }
//
//    private fun isVisibleLocal(reView: View): Boolean {
//        return reView.visibility == View.VISIBLE && reView.isShown && !reView.getGlobalVisibleRect(Rect()) && !reView.getLocalVisibleRect(Rect())
//    }
//}