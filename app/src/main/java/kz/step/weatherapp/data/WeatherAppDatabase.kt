package kz.step.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(City::class, CityInWeatherList::class), version = 4)
abstract class WeatherAppDatabase: RoomDatabase() {
    abstract fun getCityDao(): CityDao

    abstract fun getCityInWeatherListDao(): CityInWeatherListDao
}