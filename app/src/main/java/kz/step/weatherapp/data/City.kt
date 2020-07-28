package kz.step.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class City {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name: String = ""

    var country: String = ""

    var apiCityId: Long = 0
}