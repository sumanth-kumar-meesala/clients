package com.mebank

import android.app.Application
import com.mebank.retrofit.RetrofitInstance

class MeBankApp : Application() {
    override fun onCreate() {
        super.onCreate()

        RetrofitInstance.initialize()
    }
}