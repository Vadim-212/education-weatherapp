package kz.step.weatherapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection {
    lateinit var retrofit: Retrofit

    fun initializeRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(initializeOkHttpClient())
            .build()
        return retrofit
    }

    fun initializeOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(initializeHttpLogging())
            .build()
        return client
    }

    fun initializeHttpLogging(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun initializeApi(): ApiInterface {
        return initializeRetrofit().create(ApiInterface::class.java)
    }
}