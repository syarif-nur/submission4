package com.example.submission4.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission4.R
import com.example.submission4.model.FilmModel
import com.example.submission4.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.film_items.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var listFilms = ArrayList<FilmModel>()
        set(listFilms) {
            if (listFilms.size > 0) {
                this.listFilms.clear()
            }
            this.listFilms.addAll(listFilms)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_items, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = this.listFilms.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFilms[position])
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filmModel: FilmModel) {
            with(itemView) {
                txt_title.text = filmModel.title
                txt_year.text = filmModel.release
                Glide.with(this).load("https://image.tmdb.org/t/p/w92/${filmModel.photo}")
                    .into(img_poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, filmModel)
                    itemView.context.startActivity(intent)

                }
            }
        }
    }
}