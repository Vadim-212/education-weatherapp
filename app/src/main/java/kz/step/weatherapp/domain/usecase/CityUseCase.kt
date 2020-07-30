package kz.step.weatherapp.domain.usecase

import kz.step.weatherapp.data.City

class CityUseCase {
    fun filterCities(cities: ArrayList<City>, query: String): List<City> {
        return cities.filter { city -> city.name.toLowerCase().contains(query.toLowerCase()) }
    }
}