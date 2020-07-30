package kz.step.weatherapp.presentation.utils

import kz.step.weatherapp.data.City

class Constants {
    companion object {
        val defaultCityList = listOf<City>(City().apply { name = "New York"; country = "US"; apiCityId = 5128638 },
                City().apply { name = "Moscow"; country = "RU"; apiCityId = 524894 },
                City().apply { name = "Berlin"; country = "DE"; apiCityId = 2950158 },
                City().apply { name = "London"; country = "GB"; apiCityId = 2643743 },
                City().apply { name = "Nur-Sultan"; country = "KZ"; apiCityId = 1526273 },
                City().apply { name = "Rome"; country = "IT"; apiCityId = 3169070 },
                City().apply { name = "Los Angeles"; country = "US"; apiCityId = 5368361 },
                City().apply { name = "Cape Town"; country = "ZA"; apiCityId = 3369157 },
                City().apply { name = "Beijing"; country = "CN"; apiCityId = 1816670 },
                City().apply { name = "Canberra"; country = "AU"; apiCityId = 2172517 },
                City().apply { name = "Stockholm"; country = "SE"; apiCityId = 2673722 },
                City().apply { name = "Nuuk"; country = "GL"; apiCityId = 3421319 },
                City().apply { name = "Dubai"; country = "AE"; apiCityId = 292223 },
                City().apply { name = "Ottawa"; country = "CA"; apiCityId = 6094817 },
                City().apply { name = "Mexico"; country = "MX"; apiCityId = 3530597 },
                City().apply { name = "Brasília"; country = "BR"; apiCityId = 3469058 })
    }
}