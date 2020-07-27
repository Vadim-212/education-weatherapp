package kz.step.weatherapp.presentation.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.WeatherAppDatabase
import kz.step.weatherapp.presentation.contract.CityFragmentContract

class CityFragmentPresenter: CityFragmentContract.Presenter {
    var view: CityFragmentContract.View? = null
    var context: Context
    var cities: ArrayList<City> = ArrayList()
    var db: WeatherAppDatabase

    constructor(context: Context) {
        this.context = context
        db = Room.databaseBuilder(
            context,
            WeatherAppDatabase::class.java,
            "WeatherAppDatabase")
            .allowMainThreadQueries()
            .build()
    }

    override fun initializeData() {
        cities.clear()
//        cities.addAll(arrayListOf(City().apply {
//            name = "London"
//            country = "UK" },
//            City().apply {
//                name = "New York"
//                country = "US"
//            },
//            City().apply {
//                name = "Paris"
//                country = "FR"
//            }))
        cities.addAll(db.getCityDao().initiateGetCities())
        view?.processData(cities)
        view?.initializeUpdateAdapter()
    }

    override fun addCityInWeatherList(city: CityInWeatherList) {
        val cityInDB: CityInWeatherList? = db.getCityInWeatherListDao().initiateGetCityByNameAndCountry(city.name, city.country)
        if(cityInDB == null) {
            db.getCityInWeatherListDao().initiateInsertCity(city)
        } else {
            Toast.makeText(context, "This city is already in list.", Toast.LENGTH_SHORT).show()
        }

    }

    override fun attach(view: CityFragmentContract.View) {
        this.view = view
    }

    override fun onStop() {
        view = null
    }
}