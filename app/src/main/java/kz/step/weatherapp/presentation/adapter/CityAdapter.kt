package kz.step.weatherapp.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.activity.MainActivity
import kz.step.weatherapp.presentation.presenter.CityFragmentPresenter
import kz.step.weatherapp.presentation.viewholder.CityViewHolder

class CityAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var context: Context
    var cities: ArrayList<City>
    var presenter: CityFragmentPresenter

    constructor(context: Context, cities: ArrayList<City>, presenter: CityFragmentPresenter) {
        this.context = context
        this.cities = cities
        this.presenter = presenter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            presenter.addCityInWeatherList(CityInWeatherList().apply {
                name = cities.get(position).name
                country = cities.get(position).country
            })
            (context as MainActivity).supportFragmentManager.popBackStack()
        }

        (holder as CityViewHolder).initiateBind(cities.get(position).name, cities.get(position).country)
    }
}