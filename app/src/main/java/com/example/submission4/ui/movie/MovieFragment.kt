package com.example.submission4.ui.movie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission4.R
import com.example.submission4.adapter.FilmAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var adapter: FilmAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FilmAdapter()
        adapter.notifyDataSetChanged()
        rv_movies.layoutManager = LinearLayoutManager(context)
        rv_movies.adapter = adapter

        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)
        if (rv_movies.isEmpty()) {
            showLoadingMovie(true)
            val language = Locale.getDefault().getDisplayLanguage()
            if (language == "English") {
                val locale = "en-US"
                movieViewModel.setMovies(locale)
            } else {
                val locale = "id-ID"
                movieViewModel.setMovies(locale)
            }
        }
        movieViewModel.getMovies().observe(viewLifecycleOwner, androidx.lifecycle.Observer { filmItems ->
            if (filmItems != null) {
                adapter.setData(filmItems)
                showLoadingMovie(false)
            }
        })
    }

    private fun showLoadingMovie(state: Boolean) {
        if (state) {
            progressBarMovie.visibility = View.VISIBLE
        } else {
            progressBarMovie.visibility = View.GONE
        }
    }




}
