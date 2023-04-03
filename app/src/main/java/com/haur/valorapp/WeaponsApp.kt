package com.haur.valorapp

import android.app.Application
import com.haur.weapondetails.di.weaponDetailsDIModules
import com.haur.weaponslist.di.weaponListDIModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class WeaponsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeaponsApp)
            androidLogger()

            modules(arrayListOf<Module>().apply {
                addAll(weaponDetailsDIModules)
                addAll(weaponListDIModules)
            })
        }
    }
}