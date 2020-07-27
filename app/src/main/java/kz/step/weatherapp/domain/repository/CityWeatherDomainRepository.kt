package kz.step.weatherapp.domain.repository

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather

interface CityWeatherDomainRepository {
    fun initiateGetCityWeather(query: String): Observable<CityWeather>
}