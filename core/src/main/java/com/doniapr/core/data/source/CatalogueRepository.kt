package com.doniapr.core.data.source

import com.doniapr.core.data.NetworkBoundResource
import com.doniapr.core.data.Resource
import com.doniapr.core.data.source.local.LocalDataSource
import com.doniapr.core.data.source.remote.RemoteDataSource
import com.doniapr.core.data.source.remote.network.ApiResponse
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.data.source.remote.response.ReviewResponse
import com.doniapr.core.data.source.remote.response.TvShowResponse
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.model.TvShow
import com.doniapr.core.domain.repository.ICatalogueRepository
import com.doniapr.core.utils.AppExecutors
import com.doniapr.core.utils.MovieDataMapper
import com.doniapr.core.utils.TvShowDataMapper
import kotlinx.coroutines.flow.*

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {

    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(
            appExecutors
        ) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { MovieDataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovie(1)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getDetailMovie(id: String): Flow<Resource<Movie>> =
            object : NetworkBoundResource<Movie, MovieResponse>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<Movie> {
                    return localDataSource.getDetailMovie(id).map { MovieDataMapper.mapEntityToDomain(it) }
                }

                override fun shouldFetch(data: Movie?): Boolean =
                        true

                override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                        remoteDataSource.getDetailMovie(id)

                override suspend fun saveCallResult(data: MovieResponse) {
                    val movie = MovieDataMapper.mapResponseToEntity(data)
                    localDataSource.updateDetailMovie(movie)
                }

            }.asFlow()

    override fun getMovieReview(id: String): Flow<Resource<List<Review>>> =
            object : NetworkBoundResource<List<Review>, List<ReviewResponse>>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<List<Review>> {
                    return localDataSource.getMovieReview(id.toInt()).map { MovieDataMapper.mapReviewEntitiesToDomain(it) }
                }

                override fun shouldFetch(data: List<Review>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> =
                        remoteDataSource.getMovieReview(id, 1)

                override suspend fun saveCallResult(data: List<ReviewResponse>) {
                    val reviews = MovieDataMapper.mapReviewResponsesToEntities(data, id.toInt())
                    localDataSource.insertMovieReview(reviews)
                }

            }.asFlow()

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }


    override fun getOnAirTv(): Flow<Resource<List<TvShow>>> =
            object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<List<TvShow>> {
                    return localDataSource.getAllTvShow().map { TvShowDataMapper.mapEntitiesToDomain(it) }
                }

                override fun shouldFetch(data: List<TvShow>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                        remoteDataSource.getOnAirTv(1)

                override suspend fun saveCallResult(data: List<TvShowResponse>) {
                    val tvShowList = TvShowDataMapper.mapResponsesToEntities(data)
                    localDataSource.insertTvShow(tvShowList)
                }

            }.asFlow()

    override fun getDetailTv(id: String): Flow<Resource<TvShow>> =
            object : NetworkBoundResource<TvShow, TvShowResponse>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<TvShow> {
                    return localDataSource.getDetailTvShow(id).map { TvShowDataMapper.mapEntityToDomain(it) }
                }

                override fun shouldFetch(data: TvShow?): Boolean =
                        true

                override suspend fun createCall(): Flow<ApiResponse<TvShowResponse>> =
                        remoteDataSource.getDetailTv(id)

                override suspend fun saveCallResult(data: TvShowResponse) {
                    val tvShow = TvShowDataMapper.mapResponseToEntity(data)
                    localDataSource.updateDetailTvShoe(tvShow)
                }
            }.asFlow()

    override fun getTvReview(id: String): Flow<Resource<List<Review>>> =
            object : NetworkBoundResource<List<Review>, List<ReviewResponse>>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<List<Review>> {
                    return localDataSource.getTvReview(id.toInt()).map { TvShowDataMapper.mapReviewEntitiesToDomain(it) }
                }

                override fun shouldFetch(data: List<Review>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> =
                        remoteDataSource.getTvReview(id, 1)

                override suspend fun saveCallResult(data: List<ReviewResponse>) {
                    val reviews = TvShowDataMapper.mapReviewResponsesToEntities(data, id.toInt())
                    localDataSource.insertTvReview(reviews)
                }

            }.asFlow()

    override fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> {
        TODO("Not yet implemented")
    }

}