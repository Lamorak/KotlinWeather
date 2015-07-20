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
import kotlinx.android.synthetic.clearFindViewByIdCache
import kotlinx.android.synthetic.fragment_forecast.forecast_list
import org.jetbrains.anko.*

public class ForecastFragment : Fragment(), AnkoLogger {

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
            val request = Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=Prague").build()
            val response = OkHttpClient().newCall(request).execute()

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
        forecast_list.setAdapter(ForecastAdapter(ctx))
    }
}
