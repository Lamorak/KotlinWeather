package cz.lamorak.kotlinweather.api

import java.lang
import java.text.SimpleDateFormat
import java.util.*

data class ForecastResponse(
        val list: Array<ForecastResponse.ForecastEntry> = Array(0, { ForecastResponse.ForecastEntry() })
) {

    data class ForecastEntry(
            val dt: Long = 0L,
            val temp: Temp = Temp(),
            val weather: Array<Weather> = Array(0, { Weather() })
    ) {

        val format = SimpleDateFormat("EEE dd")

        fun day(): String {
            return format.format(Date(dt*1000))
        }

        fun temperature(): String {
            val temperature = temp.day - 273.15f
            return "${lang.String.format("%.1f", temperature)}°C"
        }

        fun description(): String {
            return weather.get(0).description
        }

        fun icon(): String {
            return "http://openweathermap.org/img/w/${weather.get(0).icon}.png"
        }
    }

    data class Temp(
            val day: Float = 0f
    )

    data class Weather(
            val description: String = "",
            val icon: String = ""
    )
}
