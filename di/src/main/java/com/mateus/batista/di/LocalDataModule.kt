package com.mateus.batista.di

import com.mateus.batista.data.local.PreferencesManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataModule = module {
    single { PreferencesManager(androidApplication()) }
}