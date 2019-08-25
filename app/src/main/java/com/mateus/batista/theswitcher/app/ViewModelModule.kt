package com.mateus.batista.theswitcher.app

import com.mateus.batista.theswitcher.core.list.ListRoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListRoomsViewModel() }
}