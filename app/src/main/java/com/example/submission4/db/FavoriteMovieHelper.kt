package com.example.submission4.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.submission4.db.DatabaseContract.MovieColumns.Companion.TABLE_NAME_MOVIE
import com.example.submission4.db.DatabaseContract.MovieColumns.Companion.TITLE_MOVIE
import com.example.submission4.db.DatabaseContract.MovieColumns.Companion.ID_MOVIE
import java.sql.SQLException

class FavoriteMovieHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME_MOVIE
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: FavoriteMovieHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavoriteMovieHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FavoriteMovieHelper(context)
                    }
                }
            }
            return INSTANCE as FavoriteMovieHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$ID_MOVIE ASC"
        )
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }


    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$ID_MOVIE = '$id'", null)
    }

}