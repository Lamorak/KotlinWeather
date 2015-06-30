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
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.entity.WeatherResponse
import kotlinx.android.synthetic.fragment_today
import kotlinx.android.synthetic.viewport_today_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class TodayFragment : Fragment(), AnkoLogger {

    companion object {
        fun newInstance() : TodayFragment {
            val fragment = TodayFragment()
            return fragment
        }
    }


    override fun onCreate(args: Bundle?) {
        super<Fragment>.onCreate(args)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_today, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super<Fragment>.onActivityCreated(savedInstanceState)
        async {
            val client = OkHttpClient()
            val request = Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=Prague").build()
            val response = client.newCall(request).execute()

            val weatherResponse = Gson().fromJson<WeatherResponse>(response.body().string())
            uiThread {
                detail_pressure.setText("" + weatherResponse?.main?.pressure)
            }
        }
    }
}
