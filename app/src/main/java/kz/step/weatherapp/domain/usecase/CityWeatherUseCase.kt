package kz.step.weatherapp.domain.usecase

import io.reactivex.Observable
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.CityWeather
import kz.step.weatherapp.domain.base.BaseNetworkUseCase
import kz.step.weatherapp.domain.repository.CityWeatherRepository

class CityWeatherUseCase: BaseNetworkUseCase<CityWeather> {
    var cityWeatherRepository: CityWeatherRepository

    constructor() {
        cityWeatherRepository = CityWeatherRepository()
    }

    override fun initiateCreateObservableByQuery(query: String): Observable<CityWeather> {
        return cityWeatherRepository.initiateGetCityWeatherByQuery(query)
    }

    override fun initiateCreateObservableById(cityId: Long): Observable<CityWeather> {
        return cityWeatherRepository.initiateGetCityWeatherById(cityId)
    }

    fun cityWeatherToCityInWeatherList(city: CityWeather): CityInWeatherList {
        val cityInWeatherList = CityInWeatherList()

        cityInWeatherList.apply {
                name = city.name!!
                country = city.sys?.country!!
                cityId = city.sys?.id!!
                weatherDescription = city.weather?.get(0)?.description
                weatherIcon = city.weather?.get(0)?.icon
                mainTemp = city.main?.temp
                mainPressure = city.main?.pressure
                mainHumidity = city.main?.humidity
                visibility = city.visibility
                windSpeed = city.wind?.speed
                windDeg = city.wind?.deg
                rain1h = city.rain?._1h
                cloudsAll = city.clouds?.all
                snow1h = city.snow?._1h
                sysSunrise = city.sys?.sunrise
                sysSunset = city.sys?.sunset
                updateDt = city.dt
        }

        return cityInWeatherList
    }
}