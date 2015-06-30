package cz.lamorak.kotlinweather.entity

data class WeatherResponse() {
    val main : Main = Main()

    data class Main() {
        val temp : Float = 0f
        val pressure : Float = 0f
        val humidity : Float = 0f
    }
}



