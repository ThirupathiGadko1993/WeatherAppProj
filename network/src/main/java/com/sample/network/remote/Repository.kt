package com.sample.network.remote

import com.sample.data.WeatherResponse
import com.sample.network.model.BaseApiResponse
import com.sample.network.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getWeatherReport(city: String): Flow<NetworkResult<WeatherResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getWeatherReport(city) })
        }.flowOn(Dispatchers.IO)
    }
}