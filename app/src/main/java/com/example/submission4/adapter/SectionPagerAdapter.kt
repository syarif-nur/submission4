package com.example.submission4.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission4.R
import com.example.submission4.ui.favorite.movie.FavoriteMovieFragment
import com.example.submission4.ui.favorite.tv.FavoriteTvFragment

class SectionPagerAdapter (private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLE = intArrayOf(
        R.string.tab_movie,
        R.string.tab_tv
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0-> fragment = FavoriteMovieFragment()
            1-> fragment = FavoriteTvFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }

    override fun getCount(): Int {
        return 2
    }

}