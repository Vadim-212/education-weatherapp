package kz.step.weatherapp.data.api

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather
import retrofit2.Response

class ApiImplementation: ApiInterface {
    var api: ApiInterface

    constructor() {
        api = ApiConnection().initializeApi()
    }

    override fun cityWeather(q: String, units: String, appid: String): Observable<Response<CityWeather>> {
        return api.cityWeather(q, units, appid)
    }
}