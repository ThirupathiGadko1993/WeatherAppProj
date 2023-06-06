package com.sample.weatherapp

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.sample.data.ListItem
import com.sample.data.WeatherItem
import com.sample.data.WeatherResponse
import com.sample.weatherapp.ui.fragment.detailsfragment.DetailsFragment.Companion.PARAM_CITY_NAME
import com.sample.weatherapp.ui.fragment.detailsfragment.DetailsFragment.Companion.PARAM_CLOUD_DESCRIPTION
import com.sample.weatherapp.ui.fragment.detailsfragment.DetailsFragment.Companion.PARAM_FEELS_LIKE
import com.sample.weatherapp.ui.fragment.detailsfragment.DetailsFragment.Companion.PARAM_TEMP
import com.sample.weatherapp.ui.fragment.summaryfragment.SummaryFragment.Companion.PARAM_WEATHER_RESPONSE
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val activity: Activity
) {
    fun navigateToSummaryFragment(weatherResponse: WeatherResponse) {
        activity.findNavController(R.id.nav_host_fragment_activity_main).navigate(
            R.id.action_navigation_home_to_navigation_summary, bundleOf(
                PARAM_WEATHER_RESPONSE to weatherResponse
            )
        )
    }

    fun navigateToDetailsFragment(cityName: String, listItem: ListItem, weatherItem: WeatherItem) {
        activity.findNavController(R.id.nav_host_fragment_activity_main).navigate(
            R.id.action_navigation_summary_to_navigation_details, bundleOf(
                PARAM_CITY_NAME to cityName,
                PARAM_CLOUD_DESCRIPTION to weatherItem.description.orEmpty(),
                PARAM_TEMP to listItem.main?.temp?.toString().orEmpty(),
                PARAM_FEELS_LIKE to listItem.main?.feelsLike?.toString().orEmpty()
            )
        )
    }
}
