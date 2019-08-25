package com.mateus.batista.di

import com.mateus.batista.domain.core.ThreadContextProvider
import com.mateus.batista.domain.interactor.RoomsUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {

    factory { (scope : CoroutineScope) -> RoomsUseCase(get(), scope) }
    factory { ThreadContextProvider() }
}