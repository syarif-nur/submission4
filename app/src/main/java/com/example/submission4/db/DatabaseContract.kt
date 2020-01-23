package com.example.submission4.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class MovieColumns : BaseColumns {
        companion object {
            const val TABLE_NAME_MOVIE = "movie"
            const val ID_MOVIE = "id_movie"
            const val TITLE_MOVIE ="title"
            const val RELEASE_MOVIE = "release"
            const val DESCRIPTION_MOVIE = "description"
            const val PHOTO_MOVIE = "photo"
        }
    }

    internal class TvColumns : BaseColumns {
        companion object {
            const val TABLE_NAME_TV = "tv"
            const val ID_TV = "id_tv"
            const val TITLE_TV ="title"
            const val RELEASE_TV = "release"
            const val DESCRIPTION_TV= "description"
            const val PHOTO_TV = "photo"
        }
    }
}