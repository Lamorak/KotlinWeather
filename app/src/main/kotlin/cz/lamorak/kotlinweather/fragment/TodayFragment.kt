package cz.lamorak.kotlinweather.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.picasso.Picasso
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.api.WeatherApi
import cz.lamorak.kotlinweather.api.WeatherResponse
import cz.lamorak.kotlinweather.api.weatherApi
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.viewport_today_header.*
import kotlinx.android.synthetic.viewport_today_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import retrofit.RestAdapter

public class TodayFragment : Fragment(), AnkoLogger {

    var weatherResponse: WeatherResponse? = null

    companion object {
        val tag = "today_fragment"
    }

    override fun onCreate(args: Bundle?) {
        super<Fragment>.onCreate(args)
        setRetainInstance(true)
        loadForecast()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_today, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)
        info { "view created" }
        initView()
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        clearFindViewByIdCache()
    }

    fun loadForecast() {
        async {
            weatherResponse = weatherApi.actualWeather("Prague")
            uiThread {
                initView()
            }
        }
    }

    fun initView() {
        info { weatherResponse }
        Picasso.with(getActivity()).load(weatherResponse?.icon()).into(conditions_icon)
        temperature_label.setText(weatherResponse?.temperature())
        conditions_label.setText(weatherResponse?.description())
        detail_clouds.setText(weatherResponse?.cloudines())
        detail_humidity.setText(weatherResponse?.humidity())
        detail_pressure.setText(weatherResponse?.pressure())
        detail_wind.setText(weatherResponse?.windSpeed())
        detail_direction.setText(weatherResponse?.windDirection())
    }
}
