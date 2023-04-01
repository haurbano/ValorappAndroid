package com.haur.weaponslist.data.di

import com.haur.weaponslist.data.datasources.RetrofitClient
import com.haur.weaponslist.data.datasources.WeaponsListDataSource
import com.haur.weaponslist.data.repositories.WeaponsRepositoryImpl
import com.haur.weaponslist.domain.repositories.WeaponsRepository
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitClient }
    single { get<RetrofitClient>().create(WeaponsListDataSource::class.java) }
    factory<WeaponsRepository> { WeaponsRepositoryImpl(get()) }
}