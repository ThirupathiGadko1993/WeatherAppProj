package com.sample.network.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val remoteAPIService: RemoteAPIService
) {
    suspend fun getWeatherReport(city: String) = remoteAPIService.getWeatherReport(city)
}