package com.haur.weapondetails.presentation.di

import com.haur.weapondetails.presentation.SkinsAdapter
import com.haur.weapondetails.presentation.WeaponDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val weaponDetailsPresentationModule = module {
    viewModel { WeaponDetailsViewModel(get()) }
    factory { SkinsAdapter() }
}