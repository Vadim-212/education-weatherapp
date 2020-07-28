package kz.step.weatherapp.domain.repository

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather

interface CityWeatherDomainRepository {
    fun initiateGetCityWeatherByQuery(query: String): Observable<CityWeather>

    fun initiateGetCityWeatherById(cityId: Long): Observable<CityWeather>
}