package cz.lamorak.kotlinweather.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import cz.lamorak.kotlinweather.R
import cz.lamorak.kotlinweather.api.ForecastResponse
import kotlinx.android.synthetic.item_forecast.view.*

public class ForecastAdapter : RecyclerView.Adapter<ViewHolder> {

    var ctx : Context? = null
    var items : Array<ForecastResponse.ForecastEntry>? = null

    constructor(ctx: Context?, items: Array<ForecastResponse.ForecastEntry>?) : super() {
        this.ctx = ctx
        this.items = items
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        val view = viewHolder?.itemView
        val entry = items?.get(position)
        Picasso.with(ctx).load(entry?.icon()).into(view?.forecast_icon)
        view?.forecast_day?.setText(entry?.day())
        view?.forecast_temperature?.setText(entry?.temperature())
        view?.forecast_conditions?.setText(entry?.description())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder? {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_forecast, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size() ?: 0
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
