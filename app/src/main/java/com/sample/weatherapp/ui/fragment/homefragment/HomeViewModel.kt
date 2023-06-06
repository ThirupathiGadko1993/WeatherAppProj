package com.sample.weatherapp.ui.fragment.homefragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sample.data.WeatherResponse
import com.sample.network.remote.Repository
import com.sample.weatherapp.ui.fragment.BaseViewModel
import com.sample.weatherapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : BaseViewModel(application) {

    private val _response: SingleLiveEvent<WeatherResponse?> =
        SingleLiveEvent()
    val response: LiveData<WeatherResponse?> = _response

    fun fetchWeatherReport(city: String) = viewModelScope.launch {
        showLoading()
        repository.getWeatherReport(city).collect {
            hideLoading()
            _response.value = it.data
        }
    }
}
