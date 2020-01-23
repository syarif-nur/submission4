package com.example.submission4.adapter

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

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private val mData = ArrayList<FilmModel>()

    fun setData(items: ArrayList<FilmModel>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.film_items, parent, false)
        return FilmViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(filmItems: FilmModel) {
            with(itemView) {
                txt_title.text = filmItems.title
                txt_year.text = filmItems.release
                Glide.with(this).load("https://image.tmdb.org/t/p/w92/${filmItems.photo}").into(img_poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIL, filmItems)
                    itemView.context.startActivity(intent)

                }
            }

        }

    }

}