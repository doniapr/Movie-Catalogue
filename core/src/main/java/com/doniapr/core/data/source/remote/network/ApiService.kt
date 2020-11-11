package com.doniapr.core.data.source.remote.network

import com.doniapr.core.BuildConfig
import com.doniapr.core.data.source.remote.response.ListMovieResponse
import com.doniapr.core.data.source.remote.response.ListTvShowResponse
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getNowPlayingMovie(@Query("page") page: Int): ListMovieResponse

    @GET("movie/{movieId}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailMovie(@Path("movieId") id: String): MovieResponse

    @GET("tv/on_the_air?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getOnTheAirTv(@Query("page") page: Int): ListTvShowResponse

    @GET("tv/{tvId}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getDetailTv(@Path("tvId") id: String): TvShowResponse
}