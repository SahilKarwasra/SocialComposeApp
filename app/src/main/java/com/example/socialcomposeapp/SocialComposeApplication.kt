package com.example.socialcomposeapp

import android.app.Application
import com.example.socialcomposeapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SocialComposeApplication)
            modules(appModule)
        }
    }
}