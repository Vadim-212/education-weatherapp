package kz.step.weatherapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.fragment.AddCityFragment
import kz.step.weatherapp.presentation.fragment.WeatherFragment
import kz.step.weatherapp.presentation.presenter.MainActivityPresenter
import kz.step.weatherapp.presentation.viewholder.CityWeatherViewHolder

class MainActivityAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var context: Context
    var cities: ArrayList<CityInWeatherList>
    var presenter: MainActivityPresenter

    constructor(context: Context, cities: ArrayList<CityInWeatherList>, presenter: MainActivityPresenter) {
        this.context = context
        this.cities = cities
        this.presenter = presenter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_city_weather, parent, false)
        return CityWeatherViewHolder(view, presenter)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val fragment = WeatherFragment(cities.get(position))
            val fragmentTransaction = (context as FragmentActivity).supportFragmentManager.beginTransaction()
            (context as FragmentActivity).supportFragmentManager.executePendingTransactions()
            fragmentTransaction.add(
                R.id.relativelayout_activity_main,
                fragment,
                fragment.javaClass.name)

            fragmentTransaction.addToBackStack("Name")
            fragmentTransaction.commit()
        }

        (holder as CityWeatherViewHolder).initiateBind(cities.get(position))
    }
}