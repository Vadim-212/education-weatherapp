package kz.step.weatherapp.data.api

import io.reactivex.Observable
import kz.step.weatherapp.data.CityWeather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    fun cityWeather(@Query("q") q: String, @Query("units") units: String = "metric", @Query("appid") appid: String = ApiConstants.appid): Observable<Response<CityWeather>>

    @GET("weather")
    fun cityWeatherById(@Query("id") id: Long, @Query("units") units: String = "metric", @Query("appid") appid: String = ApiConstants.appid): Observable<Response<CityWeather>>
}