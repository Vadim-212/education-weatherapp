package kz.step.weatherapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CityWeather {
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("snow")
    @Expose
    var snow: Snow? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("dt")
    @Expose
    var dt: Long? = null

    override fun toString(): String {
        return "CityWeather(weather='$weather',main='$main'," +
                "visibility='$visibility',wind='$wind',rain='$rain'," +
                "clouds='$clouds',snow='$snow',dt='$dt',name='$name')"
    }

    inner class Clouds {
        @SerializedName("all")
        @Expose
        var all: Int? = null
        override fun toString(): String {
            return "Clouds(all='$all')"
        }
    }

    inner class Main {
        @SerializedName("temp")
        @Expose
        var temp: Double? = null

        @SerializedName("feels_like")
        @Expose
        var feelsLike: Double? = null

        @SerializedName("temp_min")
        @Expose
        var tempMin: Double? = null

        @SerializedName("temp_max")
        @Expose
        var tempMax: Double? = null

        @SerializedName("pressure")
        @Expose
        var pressure: Double? = null

        @SerializedName("humidity")
        @Expose
        var humidity: Double? = null
        override fun toString(): String {
            return "Main(temp='$temp',feelsLike='$feelsLike'," +
                    "tempMin='$tempMin',tempMax='$tempMax',pressure='$pressure'," +
                    "humidity='$humidity')"
        }
    }

    inner class Rain {
        @SerializedName("1h")
        @Expose
        var _1h: Double? = null
        override fun toString(): String {
            return "Rain(1h='$_1h')"
        }
    }

    inner class Snow {
        @SerializedName("1h")
        @Expose
        var _1h: Double? = null
        override fun toString(): String {
            return "Snow(1h='$_1h')"
        }
    }

    inner class Weather {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("main")
        @Expose
        var main: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("icon")
        @Expose
        var icon: String? = null
        override fun toString(): String {
            return "Weather(id='$id',main='$main'," +
                    "description='$description',icon='$icon')"
        }
    }

    inner class Wind {
        @SerializedName("speed")
        @Expose
        var speed: Double? = null

        @SerializedName("deg")
        @Expose
        var deg: Double? = null
        override fun toString(): String {
            return "Wind(speed='$speed',deg='$deg')"
        }
    }

    inner class Sys {
        @SerializedName("type")
        @Expose
        var type: Int? = null

        @SerializedName("id")
        @Expose
        var id: Long? = null

        @SerializedName("country")
        @Expose
        var country: String? = null

        @SerializedName("sunrise")
        @Expose
        var sunrise: Long? = null

        @SerializedName("sunset")
        @Expose
        var sunset: Long? = null

        override fun toString(): String {
            return "Sys(type='$type',id='$id'," +
                    "country='$country',sunrise='$sunrise',sunset='$sunset')"
        }
    }
}