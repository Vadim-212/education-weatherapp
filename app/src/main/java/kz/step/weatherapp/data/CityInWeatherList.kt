package kz.step.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class CityInWeatherList(@PrimaryKey() var cityId: Long = 0) {
//    var id: Int = 0

    var name: String = ""

    var country: String = ""



    var weatherDescription: String? = ""

    var weatherIcon: String? = ""

    var mainTemp: Double? = 0.0

    var mainPressure: Double? = 0.0

    var mainHumidity: Double? = 0.0

    var visibility: Int? = 0

    var windSpeed: Double? = 0.0

    var windDeg: Double? = 0.0

    var rain1h: Double? = 0.0

    var cloudsAll: Int? = 0

    var snow1h: Double? = 0.0

    var sysSunrise: Long? = 0

    var sysSunset: Long? = 0

    var updateDt: Long? = 0
}