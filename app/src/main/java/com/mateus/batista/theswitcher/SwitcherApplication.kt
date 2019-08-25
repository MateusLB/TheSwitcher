package com.mateus.batista.theswitcher

import android.app.Application
import com.mateus.batista.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwitcherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SwitcherApplication)
            modules(domainModule)
        }
    }
}