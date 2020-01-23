package com.example.submission4.ui.favorite.tv


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission4.R
import com.example.submission4.db.FavoriteTvHelper
import com.example.submission4.helper.MappingHelper
import com.example.submission4.model.FilmModel
import com.example.submission4.ui.detail.DetailTvActivity
import com.example.submission4.ui.favorite.movie.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite_tv.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvFragment : Fragment() {
    private lateinit var adapter: FavoriteTvAdapter
    private lateinit var tvHelper: FavoriteTvHelper

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFilms)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_favorite_tv.layoutManager = LinearLayoutManager(activity)
        rv_favorite_tv.setHasFixedSize(true)
        adapter = FavoriteTvAdapter()
        rv_favorite_tv.adapter = adapter

        tvHelper = FavoriteTvHelper.getInstance(context!!)
        tvHelper.open()

        if (savedInstanceState == null) {
            loadTvAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FilmModel>(EXTRA_STATE)
            if (list != null) {
                adapter.listFilms = list
            }
        }
    }

    private fun loadTvAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBarFavoriteTv.visibility = View.VISIBLE
            val deferredTv = async(Dispatchers.IO) {
                val cursor = tvHelper.queryAll()
                MappingHelper.tvCursorToArrayList(cursor)
            }
            progressBarFavoriteTv.visibility = View.INVISIBLE
            val tvs = deferredTv.await()
            if (tvs.size > 0) {
                adapter.listFilms = tvs
            } else {
                adapter.listFilms = ArrayList()

            }
        }

    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                DetailTvActivity.REQUEST_ADD -> if (resultCode == DetailTvActivity.RESULT_ADD){
                    val tv = data.getParcelableExtra<FilmModel>(DetailTvActivity.EXTRA_DETAIL)
                    adapter.addItem(tv)
                    rv_favorite_tv.smoothScrollToPosition(adapter.itemCount)
                }
            }
            when (resultCode){
                DetailTvActivity.RESULT_DELETE ->{
                    val position = data.getIntExtra(DetailTvActivity.EXTRA_POSITION, 0)
                    adapter.removeItem(position)
                }
            }

        }

    }*/

    override fun onPause() {
        tvHelper.close()
        super.onPause()
    }

}
