//package com.lazyxu.lazystudy
//
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//
//class HomePageAdapter(fm: FragmentManager, private val data: Array<String>) : FragmentPagerAdapter(fm) {
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
//    }
//
//    override fun getItem(position: Int): Fragment {
//        return HomePagerFragment.getInstance(position)
//
//    }
//
//    override fun getPageTitle(position: Int): CharSequence {
//        return data[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return data.size
//    }
//
//    override fun getItemPosition(`object`: Any): Int {
//        return POSITION_NONE
//    }
//}