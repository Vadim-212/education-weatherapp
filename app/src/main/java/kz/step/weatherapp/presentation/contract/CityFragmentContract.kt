package kz.step.weatherapp.presentation.contract

import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.presentation.base.BaseContract

interface CityFragmentContract {
    interface View: BaseContract.BaseView {
        fun initializePresenter()

        fun initializeLayoutManager()

        fun initializeAdapter()

        fun initializeUpdateAdapter()

        fun processData(cities: ArrayList<City>)
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun initializeData()

        fun addCityInWeatherList(city: CityInWeatherList)
    }
}