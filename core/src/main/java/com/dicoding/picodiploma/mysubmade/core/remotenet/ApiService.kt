package com.dicoding.picodiploma.mysubmade.core.remotenet


import com.dicoding.picodiploma.mysubmade.core.BuildConfig
import com.dicoding.picodiploma.mysubmade.core.remoteres.ListMovieRes
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getMovies(): ListMovieRes
    abstract fun getSearchResultData(searchTitle: String, apiKey: String, pageIndex: Int)


}