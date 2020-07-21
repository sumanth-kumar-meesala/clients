package com.mebank.retrofit

import com.mebank.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


object RetrofitInstance {

    lateinit var service: RetrofitService

    fun initialize() {

        Timber.tag(RetrofitInstance::class.java.simpleName)
        Timber.d("Retrofit installed successfully")

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }
}