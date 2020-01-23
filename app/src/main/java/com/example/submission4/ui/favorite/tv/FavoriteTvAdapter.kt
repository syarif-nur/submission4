package com.example.submission4.ui.favorite.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission4.R
import com.example.submission4.model.FilmModel
import com.example.submission4.ui.detail.DetailTvActivity
import kotlinx.android.synthetic.main.film_items.view.*

class FavoriteTvAdapter : RecyclerView.Adapter<FavoriteTvAdapter.FavoriteTvViewHolder>() {

    var listFilms = ArrayList<FilmModel>()
        set(listFilms) {
            if (listFilms.size > 0) {
                this.listFilms.clear()
            }
            this.listFilms.addAll(listFilms)
            notifyDataSetChanged()
        }

    /*fun addItem(filmModel: FilmModel) {
        this.listFilms.add(filmModel)
        notifyItemInserted(this.listFilms.size - 1)
    }

    fun removeItem(position: Int) {
        this.listFilms.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, this.listFilms.size)
    }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvAdapter.FavoriteTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_items, parent, false)
        return FavoriteTvViewHolder(view)
    }

    override fun getItemCount(): Int = this.listFilms.size

    override fun onBindViewHolder(holder: FavoriteTvAdapter.FavoriteTvViewHolder, position: Int) {
        holder.bind(listFilms[position])
    }

    inner class FavoriteTvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filmModel: FilmModel) {
            with(itemView) {
                txt_title.text = filmModel.title
                txt_year.text = filmModel.release
                Glide.with(this).load("https://image.tmdb.org/t/p/w92/${filmModel.photo}")
                    .into(img_poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_DETAIL, filmModel)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}