package com.haur.valorapp

import android.app.Application
import com.haur.weaponslist.di.weaponListDIModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeaponsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeaponsApp)
            androidLogger()
            modules(weaponListDIModules)
        }
    }
}