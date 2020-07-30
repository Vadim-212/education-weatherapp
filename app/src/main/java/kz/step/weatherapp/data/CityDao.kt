package kz.step.weatherapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Insert
    fun initiateInsertCity(city: City)

    @Insert
    fun initiateInsertCities(cities: List<City>)

    @Query("SELECT * FROM city")
    fun initiateGetCities(): List<City>

    @Query("SELECT * FROM city WHERE id = :id")
    fun initiateGetCityById(id: Int): City

    @Query("SELECT * FROM city WHERE name = :name")
    fun initiateGetCityByName(name: String): City

    @Query("SELECT * FROM city WHERE name LIKE '%'||:query||'%'")
    fun initiateGetCitiesByQuery(query: String): List<City>

    @Query("SELECT * FROM city WHERE name = :name AND country = :country")
    fun initiateGetCityByNameAndCountry(name: String, country: String): City

    @Query("DELETE FROM city WHERE id = :id")
    fun initiateDeleteCityById(id: Int)

    @Query("DELETE FROM city WHERE name = :name")
    fun initiateDeleteCityByName(name: String)

    @Query("DELETE FROM city WHERE name = :name AND country = :country")
    fun initiateDeleteCityByNameAndCountry(name: String, country: String)
}