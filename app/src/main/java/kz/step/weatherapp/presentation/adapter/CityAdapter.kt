package kz.step.weatherapp.presentation.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_city.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kz.step.weatherapp.R
import kz.step.weatherapp.data.City
import kz.step.weatherapp.data.CityInWeatherList
import kz.step.weatherapp.data.CityWeather
import kz.step.weatherapp.di.component.DaggerUseCaseComponent
import kz.step.weatherapp.di.module.UseCaseModule
import kz.step.weatherapp.domain.usecase.CityWeatherUseCase
import kz.step.weatherapp.presentation.activity.MainActivity
import kz.step.weatherapp.presentation.presenter.CityFragmentPresenter
import kz.step.weatherapp.presentation.viewholder.CityViewHolder
import javax.inject.Inject

class CityAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var context: Context
    var cities: ArrayList<City>
    var presenter: CityFragmentPresenter
    @Inject lateinit var cityWeatherUseCase: CityWeatherUseCase

    constructor(context: Context, cities: ArrayList<City>, presenter: CityFragmentPresenter) {
        this.context = context
        this.cities = cities
        this.presenter = presenter
        DaggerUseCaseComponent.builder().useCaseModule(UseCaseModule(context)).build().inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if((context as FragmentActivity).progressbar_fragment_add_city.visibility == View.INVISIBLE) {
                (context as FragmentActivity).progressbar_fragment_add_city.visibility = View.VISIBLE
                val observable =
                    cityWeatherUseCase.initiateCreateObservableById(cities.get(position).apiCityId)
                var observer = observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response ->
                        run {
                            presenter.addCityInWeatherList(cityWeatherUseCase.cityWeatherToCityInWeatherList(response))
                            (context as FragmentActivity).progressbar_fragment_add_city.visibility = View.INVISIBLE
                            (context as MainActivity).supportFragmentManager.popBackStack()
                        }
                    }, { t ->
                        run {
                            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

        (holder as CityViewHolder).initiateBind(cities.get(position).name, cities.get(position).country)
    }
}