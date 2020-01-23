package com.example.submission4.ui.detail

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.submission4.R
import com.example.submission4.db.DatabaseContract
import com.example.submission4.db.FavoriteTvHelper
import com.example.submission4.model.FilmModel
import kotlinx.android.synthetic.main.activity_detail.data_poster
import kotlinx.android.synthetic.main.activity_detail.detail_description
import kotlinx.android.synthetic.main.activity_detail.detail_title
import kotlinx.android.synthetic.main.activity_detail.detail_year

class DetailTvActivity : AppCompatActivity() {

    private lateinit var tvHelper: FavoriteTvHelper

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
        /*const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val RESULT_DELETE = 200*/

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv)

        supportActionBar?.title = "Detail Tv"

        val data = intent.getParcelableExtra(DetailActivity.EXTRA_DETAIL) as FilmModel
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
        tvHelper = FavoriteTvHelper.getInstance(applicationContext)
        tvHelper.open()
        when (item.itemId) {
            R.id.add_favorite -> {
                tvAddToFavorite()
            }
        }
        tvHelper.close()
        return super.onOptionsItemSelected(item)
    }

    private fun tvAddToFavorite() {
        val data = intent.getParcelableExtra(EXTRA_DETAIL) as FilmModel
        val values = ContentValues()
        values.put(DatabaseContract.TvColumns.ID_TV, data.id)
        values.put(DatabaseContract.TvColumns.TITLE_TV, detail_title.text.toString())
        values.put(DatabaseContract.TvColumns.RELEASE_TV, detail_year.text.toString())
        values.put(DatabaseContract.TvColumns.DESCRIPTION_TV, detail_description.text.toString())
        values.put(DatabaseContract.TvColumns.PHOTO_TV, data.photo)
        val result = tvHelper.insert(values)
        if (result > 0) {
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
        } else {
            tvHelper.deleteById(data.id.toString())
            Toast.makeText(this, "Remove from favorite", Toast.LENGTH_SHORT).show()
        }
    }


}
