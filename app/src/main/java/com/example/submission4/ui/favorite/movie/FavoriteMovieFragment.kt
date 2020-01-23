package com.example.submission4.ui.favorite.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission4.R
import com.example.submission4.db.FavoriteMovieHelper
import com.example.submission4.helper.MappingHelper
import com.example.submission4.model.FilmModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {
    private lateinit var adapter: FavoriteAdapter
    private lateinit var movieHelper: FavoriteMovieHelper

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_favorite_movie.layoutManager = LinearLayoutManager(activity)
        rv_favorite_movie.setHasFixedSize(true)
        adapter = FavoriteAdapter()
        rv_favorite_movie.adapter = adapter

        movieHelper = FavoriteMovieHelper.getInstance(context!!)
        movieHelper.open()

        if (savedInstanceState == null) {
            loadMovieAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<FilmModel>(EXTRA_STATE)
            if (list != null) {
                adapter.listFilms = list
            }

        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFilms)
    }

    private fun loadMovieAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            progressBarFavoriteMovie.visibility = View.VISIBLE
            val deferredMovie = async(Dispatchers.IO) {
                val cursor = movieHelper.queryAll()
                MappingHelper.movieCursorToArrayList(cursor)
            }
            progressBarFavoriteMovie.visibility = View.INVISIBLE
            val movies = deferredMovie.await()
            if (movies.size > 0) {
                adapter.listFilms = movies
            } else {
                adapter.listFilms = ArrayList()

            }
        }
    }

    override fun onPause() {
        movieHelper.close()
        super.onPause()
    }

}
