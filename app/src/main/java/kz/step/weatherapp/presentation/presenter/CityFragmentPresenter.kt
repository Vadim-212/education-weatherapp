package kz.step.weatherapp.presentation.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.WeatherAppDatabase
import kz.step.weatherapp.di.component.DaggerUseCaseComponent
import kz.step.weatherapp.di.module.UseCaseModule
import kz.step.weatherapp.domain.usecase.DatabaseUseCase
import kz.step.weatherapp.presentation.contract.CityFragmentContract
import kz.step.weatherapp.presentation.utils.Constants
import javax.inject.Inject

class CityFragmentPresenter: CityFragmentContract.Presenter {
    var view: CityFragmentContract.View? = null
    var context: Context
    var cities: ArrayList<City> = ArrayList()
    //var db: WeatherAppDatabase
    @Inject lateinit var databaseUseCase: DatabaseUseCase

    constructor(context: Context) {
        this.context = context
        DaggerUseCaseComponent.builder().useCaseModule(UseCaseModule(context)).build().inject(this)
    }

    fun initializeCityListOnFirstTime() {
        val citylist = databaseUseCase.initiateGetCities()
        if(citylist.isNullOrEmpty()) {
            databaseUseCase.initiateInsertCities(Constants.defaultCityList)
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
        cities.addAll(databaseUseCase.initiateGetCities().sortedBy { city -> city.name })
        view?.processData(cities)
        view?.initializeUpdateAdapter()
    }

    override fun addCityInWeatherList(city: CityInWeatherList) {
        val cityInDB: CityInWeatherList? = databaseUseCase.initiateGetCityInWeatherListByCityId(city.cityId)
        if(cityInDB == null) {
            databaseUseCase.initiateInsertCityInWeatherList(city)
        } else {
            Toast.makeText(context, "This city is already in list.", Toast.LENGTH_SHORT).show()
        }

    }

    // TODO: add to interface
    fun updateDataByQuery(query: String) {
        cities.clear()
        cities.addAll(databaseUseCase.initiateGetCitiesByQuery(query).sortedBy { city -> city.name })
        view?.processData(cities)
        view?.initializeUpdateAdapter()
    }

    // TODO: add to interface
    fun clearData() {
        cities.clear()
        view?.processData(cities)
        view?.initializeUpdateAdapter()
    }

    override fun attach(view: CityFragmentContract.View) {
        this.view = view
    }

    override fun onStop() {
        view = null
    }
}