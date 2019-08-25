package com.mateus.batista.di

import com.mateus.batista.data.repository.RoomsRepositoryImp
import com.mateus.batista.domain.repository.RoomsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { RoomsRepositoryImp(get()) as RoomsRepository }
}