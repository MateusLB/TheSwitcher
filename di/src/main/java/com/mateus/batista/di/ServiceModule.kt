package com.mateus.batista.di

import com.mateus.batista.data.remote.FakeService
import com.mateus.batista.data.remote.SwitcherService
import org.koin.dsl.module

val serviceModule = module {
    single { FakeService() as SwitcherService }
}