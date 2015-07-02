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
import kotlinx.android.synthetic.viewport_today_header.view.*
import kotlinx.android.synthetic.viewport_today_detail.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class TodayFragment : Fragment(), AnkoLogger {

    var weatherResponse: WeatherResponse? = null

    companion object {
        fun newInstance() : TodayFragment {
            val fragment = TodayFragment()
            return fragment
        }

        fun tag() : String {
            return "today_fragment"
        }
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

    fun loadForecast() {
        async {
            val request = Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=Prague").build()
            val response = OkHttpClient().newCall(request).execute()

            weatherResponse = Gson().fromJson<WeatherResponse>(response.body().string())
            uiThread {
                initView()
            }
        }
    }

    fun initView() {
        info { weatherResponse }
        val view = getView()
        Picasso.with(getActivity()).load(weatherResponse?.icon()).into(view.conditions_icon)
        view.temperature_label.setText(weatherResponse?.temperature())
        view.conditions_label.setText(weatherResponse?.description())
        view.detail_clouds.setText(weatherResponse?.cloudines())
        view.detail_humidity.setText(weatherResponse?.humidity())
        view.detail_pressure.setText(weatherResponse?.pressure())
        view.detail_wind.setText(weatherResponse?.windSpeed())
        view.detail_direction.setText(weatherResponse?.windDirection())
    }
}
