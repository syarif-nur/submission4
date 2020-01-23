package com.example.submission4.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission4.BuildConfig.API_KEY
import com.example.submission4.model.FilmModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MovieViewModel: ViewModel() {

    val listFilms = MutableLiveData<ArrayList<FilmModel>>()

    internal fun setMovies(locale: String) {
        val client = AsyncHttpClient()
        val listItems = ArrayList<FilmModel>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=$locale"
        client.get(url, object : AsyncHttpResponseHandler() {

            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    for (i in 0 until list.length()) {
                        val movie = list.getJSONObject(i)
                        val movieItems = FilmModel()
                        movieItems.id = movie.getInt("id")
                        movieItems.title = movie.getString("title")
                        val date = movie.getString("release_date")
                        movieItems.photo = movie.getString("poster_path")
                        movieItems.description = movie.getString("overview")
                        movieItems.release = date.substring(0,4)
                        listItems.add(movieItems)
                    }
                    listFilms.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }
        })
    }

    internal fun getMovies(): LiveData<ArrayList<FilmModel>> {
        return listFilms
    }

}