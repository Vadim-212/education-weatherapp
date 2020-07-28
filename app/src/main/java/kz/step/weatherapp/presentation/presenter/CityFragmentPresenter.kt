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

    fun initializeCityListOnFirstTime() {
        val citylist = db.getCityDao().initiateGetCities()
        if(citylist.isNullOrEmpty()) {
            val defaultList = listOf<City>(City().apply { name = "New York"; country = "US"; apiCityId = 5128638 },
                City().apply { name = "Moscow"; country = "RU"; apiCityId = 524894 },
                City().apply { name = "Berlin"; country = "DE"; apiCityId = 2950158 },
                City().apply { name = "London"; country = "GB"; apiCityId = 2643743 },
                City().apply { name = "Nur-Sultan"; country = "KZ"; apiCityId = 1526273 },
                City().apply { name = "Rome"; country = "IT"; apiCityId = 3169070 },
                City().apply { name = "Los Angeles"; country = "US"; apiCityId = 5368361 },
                City().apply { name = "Cape Town"; country = "ZA"; apiCityId = 3369157 },
                City().apply { name = "Beijing"; country = "CN"; apiCityId = 1816670 },
                City().apply { name = "Canberra"; country = "AU"; apiCityId = 2172517 },
                City().apply { name = "Stockholm"; country = "SE"; apiCityId = 2673722 },
                City().apply { name = "Nuuk"; country = "GL"; apiCityId = 3421319 },
                City().apply { name = "Dubai"; country = "AE"; apiCityId = 292223 },
                City().apply { name = "Ottawa"; country = "CA"; apiCityId = 6094817 },
                City().apply { name = "Mexico"; country = "MX"; apiCityId = 3530597 },
                City().apply { name = "Brasília"; country = "BR"; apiCityId = 3469058 })
            db.getCityDao().initiateInsertCities(defaultList)
        }
    }

    override fun initializeData() {
        initializeCityListOnFirstTime()
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
        cities.addAll(db.getCityDao().initiateGetCities().sortedBy { city -> city.name })
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