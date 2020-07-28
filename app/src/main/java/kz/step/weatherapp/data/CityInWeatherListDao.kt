package kz.step.weatherapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CityInWeatherListDao {
    @Insert
    fun initiateInsertCity(city: CityInWeatherList)

    @Insert
    fun initiateInsertCities(cities: List<CityInWeatherList>)

    @Query("SELECT * FROM cityinweatherlist")
    fun initiateGetCities(): List<CityInWeatherList>

//    @Query("SELECT * FROM cityinweatherlist WHERE id = :id")
//    fun initiateGetCityById(id: Int): CityInWeatherList

    @Query("SELECT * FROM cityinweatherlist WHERE cityId = :cityId")
    fun initiateGetCityByCityId(cityId: Long): CityInWeatherList

    @Query("SELECT * FROM cityinweatherlist WHERE name = :name AND country = :country")
    fun initiateGetCityByNameAndCountry(name: String, country: String): CityInWeatherList

    @Update
    fun initiateUpdateCityWeather(cityInWeatherList: CityInWeatherList)

//    @Query("DELETE FROM cityinweatherlist WHERE id = :id")
//    fun initiateDeleteCityById(id: Int)

    @Query("DELETE FROM cityinweatherlist WHERE cityId = :cityId")
    fun initiateDeleteCityByCityId(cityId: Long)

    @Query("DELETE FROM cityinweatherlist WHERE name = :name AND country = :country")
    fun initiateDeleteCityByNameAndCountry(name: String, country: String)
}