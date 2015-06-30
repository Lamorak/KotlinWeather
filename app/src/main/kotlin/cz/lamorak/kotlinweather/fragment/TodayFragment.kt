package cz.lamorak.kotlinweather.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.lamorak.kotlinweather.R
import kotlinx.android.synthetic.fragment_today

public class TodayFragment : Fragment() {

    companion object {
        fun newInstance() : TodayFragment {
            val fragment = TodayFragment()
            return fragment
        }
    }


    override fun onCreate(args: Bundle?) {
        super.onCreate(args)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_today, container, false)
        return view
    }
}
