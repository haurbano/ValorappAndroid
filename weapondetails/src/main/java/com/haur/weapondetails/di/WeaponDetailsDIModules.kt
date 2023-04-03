package com.haur.weapondetails.di

import com.haur.weapondetails.data.di.weaponDataModule
import com.haur.weapondetails.presentation.di.weaponDetailsPresentationModule

val weaponDetailsDIModules = listOf(
    weaponDetailsPresentationModule,
    weaponDataModule
)