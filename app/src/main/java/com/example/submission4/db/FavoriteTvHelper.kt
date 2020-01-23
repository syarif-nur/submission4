package com.example.submission4.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.submission4.db.DatabaseContract.TvColumns.Companion.TABLE_NAME_TV
import com.example.submission4.db.DatabaseContract.TvColumns.Companion.TITLE_TV
import com.example.submission4.db.DatabaseContract.TvColumns.Companion.ID_TV
import java.sql.SQLException

class FavoriteTvHelper (context: Context){

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME_TV
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: FavoriteTvHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavoriteTvHelper{
            if(INSTANCE == null){
                synchronized(SQLiteOpenHelper::class.java){
                    if (INSTANCE == null){
                        INSTANCE = FavoriteTvHelper(context)
                    }
                }
            }
            return INSTANCE  as FavoriteTvHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$TITLE_TV DESC"
        )
    }


    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()
        if(database.isOpen)
            database.close()
    }

    fun insert(values: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String): Int{
        return database.delete(DATABASE_TABLE,"$ID_TV = '$id'", null)
    }


}