package com.example.submission4.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmModel (
    var id: Int = 0,
    var title: String? = null,
    var release: String? = null,
    var description: String? = null,
    var photo: String? = null
):Parcelable
