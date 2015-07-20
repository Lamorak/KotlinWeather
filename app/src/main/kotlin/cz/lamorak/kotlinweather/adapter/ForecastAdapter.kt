package cz.lamorak.kotlinweather.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.lamorak.kotlinweather.R
import kotlinx.android.synthetic.item_forecast.view.*

public class ForecastAdapter : RecyclerView.Adapter<ViewHolder> {

    var ctx : Context? = null

    constructor(ctx: Context?) : super() {
        this.ctx = ctx
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        val view = viewHolder?.itemView
        view?.forecast_day?.setText("${position} Mon")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder? {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_forecast, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 30
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
