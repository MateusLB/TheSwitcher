package com.mateus.batista.domain.interactor

import com.mateus.batista.domain.core.Response
import com.mateus.batista.domain.core.UseCase
import com.mateus.batista.domain.model.Room
import com.mateus.batista.domain.repository.RoomsRepository
import kotlinx.coroutines.CoroutineScope

class RoomsUseCase(
    private val roomsRepository: RoomsRepository,
    scope: CoroutineScope
) : UseCase<List<Room>, RoomsUseCase.Params>(scope) {

    override suspend fun run(params: Params?): Response<List<Room>> {
       requireNotNull(params) { "\"Params\" must not be null" }
        return roomsRepository.getRooms(params.idHouse)
    }

    data class Params(val idHouse: String)

    // idHouse is just a proposed example, to show how to send a parameter in case with you need.
}