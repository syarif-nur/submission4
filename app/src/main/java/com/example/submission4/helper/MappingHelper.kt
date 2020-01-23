package com.example.submission4.helper

import android.database.Cursor
import com.example.submission4.db.DatabaseContract
import com.example.submission4.model.FilmModel

object MappingHelper {

    fun movieCursorToArrayList(movieCursor: Cursor): ArrayList<FilmModel>{
        val movieList = ArrayList<FilmModel>()
        movieCursor.moveToFirst()
        while (movieCursor.moveToNext()){
            val id = movieCursor.getInt(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ID_MOVIE))
            val title = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE_MOVIE))
            val year = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.RELEASE_MOVIE))
            val description = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.DESCRIPTION_MOVIE))
            val poster = movieCursor.getString(movieCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.PHOTO_MOVIE))
            movieList.add(FilmModel(id,title,year,description,poster))
        }
        return movieList
    }

    fun tvCursorToArrayList(tvCursor: Cursor): ArrayList<FilmModel>{
        val tvList = ArrayList<FilmModel>()
        tvCursor.moveToFirst()
        while (tvCursor.moveToNext()){
            val id  = tvCursor.getInt(tvCursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.ID_TV))
            val title  = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.TITLE_TV))
            val year = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.RELEASE_TV))
            val description = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.DESCRIPTION_TV))
            val poster = tvCursor.getString(tvCursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.PHOTO_TV))
            tvList.add(FilmModel(id,title,year,description,poster))
        }
        return tvList
    }
}