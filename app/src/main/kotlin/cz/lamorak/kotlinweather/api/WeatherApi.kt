package cz.lamorak.kotlinweather.api

import cz.lamorak.kotlinweather.api.WeatherResponse
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Query

val restAdapter = RestAdapter.Builder()
        .setEndpoint("http://api.openweathermap.org/data/2.5")
        .build();
val weatherApi = restAdapter.create(javaClass<WeatherApi>())

public interface WeatherApi {
    @GET("/weather")
    fun actualWeather(@Query("q") location: String) : WeatherResponse

    @GET("/forecast/daily")
    fun forecast(@Query("q") location: String, @Query("cnt") count: Int = 7) : ForecastResponse
}
