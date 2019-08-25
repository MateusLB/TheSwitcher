package com.mateus.batista.domain

import com.mateus.batista.domain.core.ThreadContextProvider
import org.koin.dsl.module

val testModule = module {
    single { TestContextProvider() as ThreadContextProvider }
}