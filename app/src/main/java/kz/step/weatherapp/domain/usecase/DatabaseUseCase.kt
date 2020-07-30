package kz.step.weatherapp.domain.usecase

import android.content.Context
import androidx.room.Room
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.WeatherAppDatabase

class DatabaseUseCase {
    var weatherAppDatabase: WeatherAppDatabase

    constructor(context: Context) {
        weatherAppDatabase = Room.databaseBuilder(
            context,
            WeatherAppDatabase::class.java,
            "WeatherAppDatabase")
            .allowMainThreadQueries()
            //.fallbackToDestructiveMigration() // потеря данных при использовании fallbackToDestructiveMigration
            .build()
    }

    fun initiateInsertCity(city: City) {
        weatherAppDatabase.getCityDao().initiateInsertCity(city)
    }

    fun initiateInsertCities(cities: List<City>) {
        weatherAppDatabase.getCityDao().initiateInsertCities(cities)
    }

    fun initiateGetCities(): List<City> {
        return weatherAppDatabase.getCityDao().initiateGetCities()
    }

    fun initiateGetCityById(id: Int): City {
        return weatherAppDatabase.getCityDao().initiateGetCityById(id)
    }

    fun initiateGetCityByName(name: String): City {
        return weatherAppDatabase.getCityDao().initiateGetCityByName(name)
    }

    fun initiateGetCityByNameAndCountry(name: String, country: String): City {
        return weatherAppDatabase.getCityDao().initiateGetCityByNameAndCountry(name,country)
    }

    fun initiateDeleteCityById(id: Int) {
        weatherAppDatabase.getCityDao().initiateDeleteCityById(id)
    }

    fun initiateDeleteCityByName(name: String) {
        weatherAppDatabase.getCityDao().initiateDeleteCityByName(name)
    }

    fun initiateGetCitiesByQuery(query: String): List<City> {
        return weatherAppDatabase.getCityDao().initiateGetCitiesByQuery(query)
    }

    fun initiateDeleteCityByNameAndCountry(name: String, country: String) {
        weatherAppDatabase.getCityDao().initiateDeleteCityByNameAndCountry(name,country)
    }

    fun initiateInsertCityInWeatherList(city: CityInWeatherList) {
        weatherAppDatabase.getCityInWeatherListDao().initiateInsertCity(city)
    }

    fun initiateInsertCitiesInWeatherList(cities: List<CityInWeatherList>) {
        weatherAppDatabase.getCityInWeatherListDao().initiateInsertCities(cities)
    }

    fun initiateGetCitiesInWeatherList(): List<CityInWeatherList> {
        return weatherAppDatabase.getCityInWeatherListDao().initiateGetCities()
    }

    fun initiateGetCityInWeatherListByCityId(cityId: Long): CityInWeatherList {
        return weatherAppDatabase.getCityInWeatherListDao().initiateGetCityByCityId(cityId)
    }

    fun initiateGetCityInWeatherListByNameAndCountry(name: String, country: String): CityInWeatherList {
        return weatherAppDatabase.getCityInWeatherListDao().initiateGetCityByNameAndCountry(name,country)
    }

    fun initiateUpdateCityInWeatherListWeather(cityInWeatherList: CityInWeatherList) {
        weatherAppDatabase.getCityInWeatherListDao().initiateUpdateCityWeather(cityInWeatherList)
    }

    fun initiateDeleteCityInWeatherListByCityId(cityId: Long) {
        weatherAppDatabase.getCityInWeatherListDao().initiateDeleteCityByCityId(cityId)
    }

    fun initiateDeleteCityInWeatherListByNameAndCountry(name: String, country: String) {
        weatherAppDatabase.getCityInWeatherListDao().initiateDeleteCityByNameAndCountry(name,country)
    }
}