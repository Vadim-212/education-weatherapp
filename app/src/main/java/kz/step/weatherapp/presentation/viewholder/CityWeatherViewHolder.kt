package kz.step.weatherapp.presentation.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.viewholder_city_weather.view.*
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.presenter.MainActivityPresenter
import kotlin.math.roundToInt

class CityWeatherViewHolder(itemView: View, var presenter: MainActivityPresenter): RecyclerView.ViewHolder(itemView) {
    fun initiateBind(city: CityInWeatherList) {
        if(city.weatherIcon?.last() == 'd') {
            itemView.constraintlayout_viewholder_city_weather.background = itemView.context.getDrawable(R.drawable.rounded_viewholder_daytime)
        } else {
            itemView.constraintlayout_viewholder_city_weather.background = itemView.context.getDrawable(R.drawable.rounded_viewholder_nighttime)
        }
        itemView.textview_viewholder_city_weather_temperature.setText((city.mainTemp!!).roundToInt().toString())
        itemView.textview_viewholder_city_weather_city.setText(city.name)
        itemView.textview_viewholder_city_weather_country.setText(city.country)
        itemView.imageview_viewholder_city_weather_delete_city.setOnClickListener{
            presenter.deleteCity(city)
        }
    }
}