package cz.lamorak.kotlinweather.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.picasso.Picasso
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.entity.WeatherResponse
import kotlinx.android.synthetic.viewport_today_header.*
import kotlinx.android.synthetic.viewport_today_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class TodayFragment : Fragment(), AnkoLogger {

    var weatherResponse = WeatherResponse()

    companion object {
        fun newInstance() : TodayFragment {
            val fragment = TodayFragment()
            return fragment
        }
    }

    override fun onCreate(args: Bundle?) {
        super<Fragment>.onCreate(args)
        setRetainInstance(true)
        loadForecast()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_today, container, false)
        return view
    }

    fun loadForecast() {
        async {
            val request = Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=Prague").build()
            val response = OkHttpClient().newCall(request).execute()

            weatherResponse = Gson().fromJson<WeatherResponse>(response.body().string()) ?: weatherResponse
            uiThread {
                initView()
            }
        }
    }

    fun initView() {
        Picasso.with(getActivity()).load(weatherResponse.icon()).into(conditions_icon)
        temperature_label.setText(weatherResponse.temperature())
        conditions_label.setText(weatherResponse.description())
        detail_clouds.setText(weatherResponse.cloudines())
        detail_humidity.setText(weatherResponse.humidity())
        detail_pressure.setText(weatherResponse.pressure())
        detail_wind.setText(weatherResponse.windSpeed())
        detail_direction.setText(weatherResponse.windDirection())
    }
}
