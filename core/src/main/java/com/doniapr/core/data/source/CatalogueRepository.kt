package com.doniapr.core.data.source

import com.doniapr.core.data.NetworkBoundResource
import com.doniapr.core.data.Resource
import com.doniapr.core.data.source.local.LocalDataSource
import com.doniapr.core.data.source.remote.RemoteDataSource
import com.doniapr.core.data.source.remote.network.ApiResponse
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.data.source.remote.response.ReviewResponse
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.model.Review
import com.doniapr.core.domain.repository.ICatalogueRepository
import com.doniapr.core.utils.AppExecutors
import com.doniapr.core.utils.DataMapper
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
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovie(1)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getDetailMovie(id: String): Flow<Resource<Movie>> =
            object : NetworkBoundResource<Movie, MovieResponse>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<Movie> {
                    return localDataSource.getDetailMovie(id).map { DataMapper.mapEntityToDomain(it) }
                }

                override fun shouldFetch(data: Movie?): Boolean =
                        true

                override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                        remoteDataSource.getDetailMovie(id)

                override suspend fun saveCallResult(data: MovieResponse) {
                    val movie = DataMapper.mapResponseToEntity(data)
                    localDataSource.updateDetailMovie(movie)
                }

            }.asFlow()

    override fun getMovieReview(id: String): Flow<Resource<List<Review>>> =
            object : NetworkBoundResource<List<Review>, List<ReviewResponse>>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<List<Review>>? {
                    return null
                }

                override fun shouldFetch(data: List<Review>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> =
                        remoteDataSource.getMovieReview(id, 1)

                override suspend fun saveCallResult(data: List<ReviewResponse>) {
                }

            }.asFlow()

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(
                    appExecutors
            ) {
                override fun loadFromDB(): Flow<List<Movie>>? {
                    return null
                }

                override fun shouldFetch(data: List<Movie>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                        remoteDataSource.searchMovie(query, 1)

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                }

            }.asFlow()
}