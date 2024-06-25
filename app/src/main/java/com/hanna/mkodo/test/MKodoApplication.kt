package com.hanna.mkodo.test

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MKodoApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MKodoApplication)
            modules(appModule)
        }
    }
}