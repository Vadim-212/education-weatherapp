package kz.step.weatherapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import kz.step.weatherapp.domain.usecase.CityWeatherUseCase
import kz.step.weatherapp.domain.usecase.DatabaseUseCase

@Module
class UseCaseModule(var context: Context) {
    @Provides
    fun providesCityWeatherUseCase(): CityWeatherUseCase {
        return CityWeatherUseCase()
    }

    @Provides
    fun providesDatabaseUseCase(): DatabaseUseCase {
        return DatabaseUseCase(context)
    }
}