package com.haur.weaponslist.di

import com.haur.weaponslist.data.di.dataModule
import com.haur.weaponslist.presentation.di.presentationModule

val weaponListDIModules = listOf(dataModule, presentationModule)