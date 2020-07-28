package kz.step.weatherapp.domain.repository

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather
import kz.step.weatherapp.data.api.ApiImplementation
import java.lang.Exception

class CityWeatherRepository: CityWeatherDomainRepository {
    var apiImplementation: ApiImplementation

    constructor() {
        apiImplementation = ApiImplementation()
    }

    override fun initiateGetCityWeatherByQuery(query: String): Observable<CityWeather> {
        return apiImplementation.cityWeather(query)
            .map { response ->
                if(response.isSuccessful) {
                    response.body()
                } else {
                    throw Exception()
                }
            }
    }

    override fun initiateGetCityWeatherById(cityId: Long): Observable<CityWeather> {
        return apiImplementation.cityWeatherById(cityId)
            .map { response ->
                if(response.isSuccessful) {
                    response.body()
                } else {
                    throw Exception()
                }
            }
    }
}