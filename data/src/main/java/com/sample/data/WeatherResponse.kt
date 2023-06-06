package com.sample.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("message")
    val message: Long?,
    @SerializedName("cnt")
    val cnt: Double?,
    @SerializedName("list")
    val list: List<ListItem>?,
    @SerializedName("city")
    val city: City?
) : Parcelable

@Parcelize
data class ListItem(
    @SerializedName("dt")
    val dt: Long?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("weather")
    val weather: List<WeatherItem?>?,
    @SerializedName("clouds")
    val clouds: CloudItem?,
    @SerializedName("wind")
    val wind: WindItem?,
    @SerializedName("visibility")
    val visibility: Double?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("sys")
    val sys: SysItem?,
    @SerializedName("dt_tx")
    val dtText: String?
) : Parcelable {
    fun toSummaryItem(): SummaryItem {
        return SummaryItem(
            weather?.firstOrNull()?.description.orEmpty(),
            "Temp: ${main?.temp?.toString() ?: "-"}"
        )
    }
}

@Parcelize
data class Main(
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("pressure")
    val pressure: Double?,
    @SerializedName("sea_level")
    val seaLevel: Double?,
    @SerializedName("grnd_level")
    val groundLevel: Double?,
    @SerializedName("humidity")
    val humidity: Double?,
    @SerializedName("temp_kf")
    val tempKf: Double?
) : Parcelable

@Parcelize
data class WeatherItem(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?
) : Parcelable

@Parcelize
data class CloudItem(
    @SerializedName("all")
    val all: Double?
) : Parcelable

@Parcelize
data class WindItem(
    @SerializedName("speed")
    val speed: Double?,
    @SerializedName("deg")
    val deg: Double?,
    @SerializedName("gust")
    val gust: Double?
) : Parcelable

@Parcelize
data class SysItem(
    @SerializedName("pod")
    val pod: String?
) : Parcelable

@Parcelize
data class City(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("coords")
    val coords: Coords?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("population")
    val population: Long?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("sunrise")
    val sunrise: Long?,
    @SerializedName("sunset")
    val sunset: Long?
) : Parcelable

@Parcelize
data class Coords(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?
) : Parcelable
