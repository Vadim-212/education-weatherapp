package kz.step.weatherapp.domain.usecase

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather
import kz.step.weatherapp.domain.base.BaseNetworkUseCase
import kz.step.weatherapp.domain.repository.CityWeatherRepository

class CityWeatherUseCase: BaseNetworkUseCase<CityWeather> {
    var cityWeatherRepository: CityWeatherRepository

    constructor() {
        cityWeatherRepository = CityWeatherRepository()
    }

    override fun initiateCreateObservable(query: String): Observable<CityWeather> {
        return cityWeatherRepository.initiateGetCityWeather(query)
    }
}