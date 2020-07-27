package kz.step.weatherapp.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.viewholder_city_weather.view.*
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.presenter.MainActivityPresenter

class CityWeatherViewHolder(itemView: View, var presenter: MainActivityPresenter): RecyclerView.ViewHolder(itemView) {
    fun initiateBind(city: CityInWeatherList) {
        itemView.textview_viewholder_city_weather_temperature.setText("0")
        itemView.textview_viewholder_city_weather_city.setText(city.name)
        itemView.textview_viewholder_city_weather_country.setText(city.country)
        itemView.button_viewholder_city_weather_delete_city.setOnClickListener{
            presenter.deleteCity(city)
        }
    }
}