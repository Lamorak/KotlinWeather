package cz.lamorak.kotlinweather.api

import java.lang

data class WeatherResponse(
        val main: WeatherResponse.Main = WeatherResponse.Main(),
        val weather: Array<WeatherResponse.Weather> = Array(0, { WeatherResponse.Weather() }),
        val wind: WeatherResponse.Wind = WeatherResponse.Wind(),
        val clouds: WeatherResponse.Clouds = WeatherResponse.Clouds()
) {

    fun icon(): String {
        return "http://openweathermap.org/img/w/${weather.get(0).icon}.png"
    }

    fun temperature(): String {
        val temperature = main.temp - 273.15f
        return "${lang.String.format("%.1f", temperature)}°C"
    }

    fun description(): String {
        return weather.get(0).description
    }

    fun cloudines(): String {
        return "${clouds.all}%"
    }

    fun humidity(): String {
        return "${main.humidity}%";
    }

    fun pressure(): String {
        return "${main.pressure} hPa"
    }

    fun windSpeed(): String {
        return "${wind.speed} m/s"
    }

    fun windDirection(): String {
        when (wind.deg) {
            in 23..67 -> return "NE"
            in 68..112 -> return "E"
            in 113..157 -> return "SE"
            in 158..202 -> return "S"
            in 203..247 -> return "SW"
            in 248..292 -> return "W"
            in 293..337 -> return "NW"
            else -> return "N"
        }
    }

    data class Main(
            val temp: Float = 0f,
            val pressure: Int = 0,
            val humidity: Int = 0
    )

    data class Weather(
            val description: String = "",
            val icon: String = ""
    )

    data class Wind(
            val speed: Float = 0f,
            val deg: Int = 0
    )

    data class Clouds(
            val all: Int = 0
    )
}



