package com.mateus.batista.theswitcher.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(), LifecycleObserver, KoinComponent {
}