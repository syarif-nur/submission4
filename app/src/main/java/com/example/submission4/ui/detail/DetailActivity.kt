package com.example.submission4.ui.detail

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.submission4.R
import com.example.submission4.db.DatabaseContract
import com.example.submission4.db.DatabaseHelper
import com.example.submission4.db.FavoriteMovieHelper
import com.example.submission4.db.FavoriteTvHelper
import com.example.submission4.model.FilmModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var movieHelper: FavoriteMovieHelper

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail Movie"


        val data = intent.getParcelableExtra(EXTRA_DETAIL) as FilmModel
        detail_title.text = data.title
        detail_year.text = data.release
        detail_description.text = data.description
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w185/${data.photo}")
            .into(data_poster)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        movieHelper = FavoriteMovieHelper.getInstance(applicationContext)
        movieHelper.open()
        when (item.itemId) {
            R.id.add_favorite -> {
                movieAddToFavorite()
            }
        }
        movieHelper.close()
        return super.onOptionsItemSelected(item)
    }


    private fun movieAddToFavorite() {
        val data = intent.getParcelableExtra(EXTRA_DETAIL) as FilmModel
        val values = ContentValues()
        values.put(DatabaseContract.MovieColumns.ID_MOVIE, data.id)
        values.put(DatabaseContract.MovieColumns.TITLE_MOVIE, detail_title.text.toString())
        values.put(DatabaseContract.MovieColumns.RELEASE_MOVIE, detail_year.text.toString())
        values.put(
            DatabaseContract.MovieColumns.DESCRIPTION_MOVIE,
            detail_description.text.toString()
        )
        values.put(DatabaseContract.MovieColumns.PHOTO_MOVIE, data.photo)
        val result = movieHelper.insert(values)
//        Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
        if (result > 0) {
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
        } else {
            movieHelper.deleteById(data.id.toString())
            Toast.makeText(this, "Remove from favorite", Toast.LENGTH_SHORT).show()
        }

    }

}
