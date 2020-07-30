package kz.step.weatherapp.presentation.fragment

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather.*
import kz.step.weatherapp.R
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.CityWeather
import kz.step.weatherapp.data.WeatherAppDatabase
import kz.step.weatherapp.di.component.DaggerUseCaseComponent
import kz.step.weatherapp.di.module.UseCaseModule
import kz.step.weatherapp.domain.usecase.CityWeatherUseCase
import kz.step.weatherapp.domain.usecase.DatabaseUseCase
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.roundToInt


class WeatherFragment(var city: CityInWeatherList): Fragment() {
    var rootView: View? = null
    var db: WeatherAppDatabase? = null
    var currentCityLastWeather: CityInWeatherList? = null
    @Inject lateinit var cityWeatherUseCase: CityWeatherUseCase
    @Inject lateinit var databaseUseCase: DatabaseUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = LayoutInflater.from(context).inflate(R.layout.fragment_weather, container, false)
        return rootView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDependencies()
        initializeListeners()

        db = Room.databaseBuilder(
            requireContext(),
            WeatherAppDatabase::class.java,
            "WeatherAppDatabase")
            .allowMainThreadQueries()
            .build()

//        var observer = ApiConnection().initializeApi().cityWeather("${city.name},${city.country}")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })

        currentCityLastWeather = db?.getCityInWeatherListDao()?.initiateGetCityByCityId(city.cityId)
        if(currentCityLastWeather != null) {
            val updateDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(currentCityLastWeather?.updateDt!!), ZoneId.systemDefault())
            val difference = Duration.between(LocalDateTime.now(), updateDateTime).toMinutes()
            if(difference <= 15) {
                initiateViewsFromCityInWeatherList(currentCityLastWeather!!)
            } else {
                initializeGetDataFromApi()
            }
        } else {
            initializeGetDataFromApi()
        }



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initializeGetDataFromApi() {
        // TODO: dagger
        val observable = CityWeatherUseCase().initiateCreateObservableById(city.cityId)
        var observer = observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onFailure(t: Throwable) {
        if(currentCityLastWeather == null) {
            Toast.makeText(context, "An error occurred: unable to retrieve data.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "An error occurred: getting data from the database. " + t.message, Toast.LENGTH_SHORT).show()
            initiateViewsFromCityInWeatherList(currentCityLastWeather!!)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onResponse(response: CityWeather) {
        // TODO: update weather to db
        //db?.getCityInWeatherListDao()?.initiateUpdateCityWeather(cityWeatherUseCase.cityWeatherToCityInWeatherList(response))
        initiateViewsFromCityWeather(response)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initiateViewsFromCityWeather(cityWeather: CityWeather) {
        progressbar_fragment_weather.visibility = View.INVISIBLE
        textview_fragment_weather_city.setText("${city.name}, ${city.country}")
        imageview_fragment_weather_icon.setImageDrawable(Drawable.createFromStream(context?.assets?.open("${cityWeather.weather?.get(0)?.icon}.png"), null))
        textview_fragment_weather_temperature.setText(Math.round(cityWeather.main?.temp!!).toString() + " °C")
        textview_fragment_weather_description.setText(cityWeather.weather?.get(0)?.description)
        textview_fragment_weather_pressure.setText(cityWeather.main?.pressure?.roundToInt().toString())
        textview_fragment_weather_humidity.setText(cityWeather.main?.humidity?.roundToInt().toString())
        textview_fragment_weather_wind.setText(cityWeather.wind?.speed?.roundToInt().toString())

        textview_fragment_weather_last_update.setText("Last update ${LocalDateTime.ofInstant(Instant.ofEpochSecond(cityWeather.dt!!), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"))}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initiateViewsFromCityInWeatherList(cityInWeatherList: CityInWeatherList) {
        progressbar_fragment_weather.visibility = View.INVISIBLE
        textview_fragment_weather_city.setText("${city.name}, ${city.country}")
        imageview_fragment_weather_icon.setImageDrawable(Drawable.createFromStream(context?.assets?.open("${cityInWeatherList.weatherIcon}.png"), null))
        textview_fragment_weather_temperature.setText(cityInWeatherList.mainTemp!!.roundToInt().toString() + " °C")
        textview_fragment_weather_description.setText(cityInWeatherList.weatherDescription)
        textview_fragment_weather_pressure.setText(cityInWeatherList.mainPressure?.roundToInt().toString())
        textview_fragment_weather_humidity.setText(cityInWeatherList.mainHumidity?.roundToInt().toString())
        textview_fragment_weather_wind.setText(cityInWeatherList.windSpeed?.roundToInt().toString())

        textview_fragment_weather_last_update.setText("Last update ${LocalDateTime.ofInstant(Instant.ofEpochSecond(cityInWeatherList.updateDt!!), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"))}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initializeListeners() {
        imageview_fragment_weather_button_return.setOnClickListener {
            (context as FragmentActivity).supportFragmentManager.popBackStack()
        }

        swiperefreshlayout_fragment_weather.setOnRefreshListener {
            val runnable = Runnable {
                progressbar_fragment_weather.visibility = View.VISIBLE
                initializeGetDataFromApi()
                swiperefreshlayout_fragment_weather?.isRefreshing = false
            }

            Handler().postDelayed(
                runnable, 500.toLong()
            )
        }
    }

    fun initializeDependencies() {
        DaggerUseCaseComponent.builder().useCaseModule(UseCaseModule(context?.applicationContext!!)).build().inject(this)
    }
}