package com.mateus.batista.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UserCase<T, in Params>(private val scope: CoroutineScope) {

    private val contextProvider = ThreadContextProvider()

    abstract suspend fun run(params: Params?) : Response<T>

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