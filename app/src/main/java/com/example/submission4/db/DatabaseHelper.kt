package com.example.submission4.db


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.submission4.db.DatabaseContract.MovieColumns.Companion.TABLE_NAME_MOVIE
import com.example.submission4.db.DatabaseContract.TvColumns.Companion.TABLE_NAME_TV

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "favoritecatalog"

        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_NAME_MOVIE" +
                "(${DatabaseContract.MovieColumns.ID_MOVIE} INTEGER PRIMARY KEY,"+
                "${DatabaseContract.MovieColumns.TITLE_MOVIE} TEXT NOT NULL,"+
                "${DatabaseContract.MovieColumns.RELEASE_MOVIE} TEXT NOT NULL,"+
                "${DatabaseContract.MovieColumns.DESCRIPTION_MOVIE} TEXT NOT NULL,"+
                "${DatabaseContract.MovieColumns.PHOTO_MOVIE} TEXT NOT NULL)"

        private val SQL_CREATE_TABLE_TV = "CREATE TABLE $TABLE_NAME_TV" +
                "(${DatabaseContract.TvColumns.ID_TV} INTEGER PRIMARY KEY,"+
                "${DatabaseContract.TvColumns.TITLE_TV} TEXT NOT NULL,"+
                "${DatabaseContract.TvColumns.RELEASE_TV} TEXT NOT NULL,"+
                "${DatabaseContract.TvColumns.DESCRIPTION_TV} TEXT NOT NULL,"+
                "${DatabaseContract.TvColumns.PHOTO_TV} TEXT NOT NULL)"

        private val SQL_DUMMYDATA_MOVIE = "Insert Into $TABLE_NAME_MOVIE ('id_movie','title','release','description','photo') values ('1','aaa','aaa','aaa','aaa') "
        private val SQL_DUMMYDATA_TV = "Insert Into $TABLE_NAME_TV ('id_tv','title','release','description','photo') values ('1','aaa','aaa','aaa','aaa') "
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE)
        db.execSQL(SQL_CREATE_TABLE_TV)
        db.execSQL(SQL_DUMMYDATA_MOVIE)
        db.execSQL(SQL_DUMMYDATA_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_MOVIE")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_TV")
        onCreate(db)
    }

}