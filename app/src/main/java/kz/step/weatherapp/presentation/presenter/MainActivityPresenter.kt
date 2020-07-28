package kz.step.weatherapp.presentation.presenter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.WeatherAppDatabase
import kz.step.weatherapp.presentation.contract.MainActivityContract

class MainActivityPresenter: MainActivityContract.Presenter {
    var context: Context
    var view: MainActivityContract.View? = null
    var cities: ArrayList<CityInWeatherList> = ArrayList()
    var db: WeatherAppDatabase

    constructor(context: Context) {
        this.context = context
        db = Room.databaseBuilder(
            context,
            WeatherAppDatabase::class.java,
            "WeatherAppDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration() // TODO: потеря данных при использовании fallbackToDestructiveMigration
            .build()
    }

    override fun initializeData() {
        cities.clear()
//        cities.addAll(arrayListOf(City().apply {
//            name = "London"
//            country = "UK"
//        }))
        cities.addAll(db.getCityInWeatherListDao().initiateGetCities())
        view?.processData(cities)
        view?.initializeUpdateAdapter()
    }

    override fun deleteCity(city: CityInWeatherList) {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Are you sure?").setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            cities.remove(city)
            db.getCityInWeatherListDao().initiateDeleteCityByCityId(city.cityId)
            view?.processData(cities)
            view?.initializeUpdateAdapter()
        }.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int -> return@setNegativeButton }.create().show()
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
    }

    override fun onStop() {
        view = null
    }
}