package com.haur.weaponslist.presentation.di

import com.haur.weaponslist.presentation.WeaponsAdapter
import com.haur.weaponslist.presentation.WeaponsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { WeaponsAdapter() }
    viewModel { WeaponsViewModel(get()) }
}