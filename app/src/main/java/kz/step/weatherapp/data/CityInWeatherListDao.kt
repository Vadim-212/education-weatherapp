package kz.step.weatherapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityInWeatherListDao {
    @Insert
    fun initiateInsertCity(city: CityInWeatherList)

    @Insert
    fun initiateInsertCities(cities: List<CityInWeatherList>)

    @Query("SELECT * FROM cityinweatherlist")
    fun initiateGetCities(): List<CityInWeatherList>

    @Query("SELECT * FROM cityinweatherlist WHERE id = :id")
    fun initiateGetCityById(id: Int): CityInWeatherList

    @Query("SELECT * FROM cityinweatherlist WHERE name = :name")
    fun initiateGetCityByName(name: String): CityInWeatherList

    @Query("SELECT * FROM cityinweatherlist WHERE name = :name AND country = :country")
    fun initiateGetCityByNameAndCountry(name: String, country: String): CityInWeatherList

    @Query("DELETE FROM cityinweatherlist WHERE id = :id")
    fun initiateDeleteCityById(id: Int)

    @Query("DELETE FROM cityinweatherlist WHERE name = :name")
    fun initiateDeleteCityByName(name: String)

    @Query("DELETE FROM cityinweatherlist WHERE name = :name AND country = :country")
    fun initiateDeleteCityByNameAndCountry(name: String, country: String)
}