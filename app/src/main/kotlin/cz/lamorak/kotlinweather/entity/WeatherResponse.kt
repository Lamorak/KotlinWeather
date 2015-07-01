package cz.lamorak.kotlinweather.entity

data class WeatherResponse() {
    val main = Main()
    val weather = Array(1, {Weather()})
    val wind = Wind()
    val clouds = Clouds()

    data class Main() {
        val temp = 0f
        val pressure = 0
        val humidity = 0
    }

    data class Weather() {
        val main = ""
        val icon = ""
    }

    data class Wind() {
        val speed = 0f
        val deg = 0
    }

    data class Clouds() {
        val all = 0
    }

    fun icon(): String {
        return "http://openweathermap.org/img/w/${weather.get(0).icon}.png"
    }

    fun temperature(): String {
        val temperature = main.temp - 273.15f
        return "${java.lang.String.format("%.1f", temperature)}°C"
    }

    fun description(): String {
        return weather.get(0).main
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
        when(wind.deg) {
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
 }



