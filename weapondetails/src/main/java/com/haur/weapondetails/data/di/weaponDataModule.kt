package com.haur.weapondetails.data.di

import com.haur.weapondetails.data.datasources.RetrofitClient
import com.haur.weapondetails.data.datasources.WeaponDetailsDataSource
import com.haur.weapondetails.data.repositories.WeaponDetailsRepositoryImpl
import com.haur.weapondetails.domain.repositories.WeaponDetailsRepository
import org.koin.dsl.module

internal val weaponDataModule = module {
    single { RetrofitClient }
    single { get<RetrofitClient>().create(WeaponDetailsDataSource::class.java) }
    factory<WeaponDetailsRepository> { WeaponDetailsRepositoryImpl(get()) }
}