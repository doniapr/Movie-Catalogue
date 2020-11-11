package com.doniapr.core.data.source

import com.doniapr.core.data.NetworkBoundResource
import com.doniapr.core.data.Resource
import com.doniapr.core.data.source.remote.RemoteDataSource
import com.doniapr.core.data.source.remote.network.ApiResponse
import com.doniapr.core.data.source.remote.response.MovieResponse
import com.doniapr.core.domain.model.Movie
import com.doniapr.core.domain.repository.IMovieRepository
import com.doniapr.core.utils.AppExecutors
import com.doniapr.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getNowPlayingMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(
            appExecutors
        ){
            override fun loadFromDB(): Flow<List<Movie>> {
                val movie: Movie
                return null
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null ||data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovie(1)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)

            }

        }.asFlow()

    override fun getDetailMovie(): Flow<Resource<Movie>> {
        TODO("Not yet implemented")
    }

}