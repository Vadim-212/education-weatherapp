package kz.step.weatherapp.di.component

import dagger.Component
import kz.step.weatherapp.di.module.UseCaseModule
import kz.step.weatherapp.presentation.adapter.CityAdapter
import kz.step.weatherapp.presentation.fragment.WeatherFragment
import kz.step.weatherapp.presentation.presenter.CityFragmentPresenter
import kz.step.weatherapp.presentation.presenter.MainActivityPresenter

@Component(modules = arrayOf(UseCaseModule::class))
interface UseCaseComponent {
    fun inject(cityAdapter: CityAdapter)

    fun inject(weatherFragment: WeatherFragment)

    fun inject(cityFragmentPresenter: CityFragmentPresenter)

    fun inject(mainActivityPresenter: MainActivityPresenter)
}