package com.mateus.batista.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class UseCase<T, in Params>(private val scope: CoroutineScope) : KoinComponent {

    private val contextProvider: ThreadContextProvider by inject()

    abstract suspend fun run(params: Params? = null) : Response<T>

    fun execute(
        params: Params? = null,
        onError: ((Throwable) -> Unit) = {},
        onSuccess:(T) -> Unit
    ){
        scope.launch(contextProvider.io) {
            run(params).run {
                withContext(contextProvider.main){
                    handleResponse(
                        success = {  onSuccess(it) },
                        error = { onError(it) }
                    )
                }
            }
        }
    }
}