package com.mebank

import android.app.Application
import android.util.Log
import com.mebank.retrofit.RetrofitInstance
import timber.log.Timber
import timber.log.Timber.DebugTree


class MeBankApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }

        RetrofitInstance.initialize()
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(
            priority: Int,
            tag: String?,
            message: String,
            t: Throwable?
        ) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            //Implement your custom log
        }
    }
}