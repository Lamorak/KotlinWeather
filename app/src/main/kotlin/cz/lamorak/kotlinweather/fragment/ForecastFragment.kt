package cz.lamorak.kotlinweather.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.adapter.ForecastAdapter
import cz.lamorak.kotlinweather.api.ForecastResponse
import cz.lamorak.kotlinweather.api.WeatherApi
import cz.lamorak.kotlinweather.api.weatherApi
import kotlinx.android.synthetic.clearFindViewByIdCache
import kotlinx.android.synthetic.fragment_forecast.forecast_list
import org.jetbrains.anko.*
import retrofit.RestAdapter

public class ForecastFragment : Fragment(), AnkoLogger {

    var forecastResponse: ForecastResponse? = null

    companion object {
        val tag = "forecast_fragment"
    }

    override fun onCreate(args: Bundle?) {
        super<Fragment>.onCreate(args)
        setRetainInstance(true)
        loadForecast()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        clearFindViewByIdCache()
    }

    fun loadForecast() {
        async {
            forecastResponse = weatherApi.forecast("Prague", 16)
            uiThread {
                initView()
            }
        }
    }

    fun initView() {
        var numColumns = 1
        configuration(smallestWidth = 600) {
            numColumns = 2
        }
        forecast_list.setHasFixedSize(true)
        forecast_list.setLayoutManager(GridLayoutManager(ctx, numColumns))
        forecast_list.setAdapter(ForecastAdapter(ctx, forecastResponse?.list))
    }
}
