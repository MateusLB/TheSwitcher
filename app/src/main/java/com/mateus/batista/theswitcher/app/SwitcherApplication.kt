package com.mateus.batista.theswitcher.app

import android.app.Application
import com.mateus.batista.di.domainModule
import com.mateus.batista.di.localDataModule
import com.mateus.batista.di.repositoryModule
import com.mateus.batista.di.serviceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwitcherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SwitcherApplication)
            modules(listOf(domainModule, localDataModule, repositoryModule, serviceModule, viewModelModule))
        }
    }
}