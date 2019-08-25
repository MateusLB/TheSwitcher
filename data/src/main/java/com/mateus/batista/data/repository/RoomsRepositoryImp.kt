package com.mateus.batista.data.repository

import com.mateus.batista.data.model.mapper.RoomsMapper
import com.mateus.batista.data.remote.SwitcherService
import com.mateus.batista.data.utils.safeApiCall
import com.mateus.batista.domain.core.Response
import com.mateus.batista.domain.model.Room
import com.mateus.batista.domain.repository.RoomsRepository
import org.koin.core.KoinComponent

class RoomsRepositoryImp(
    private val service : SwitcherService
) : KoinComponent, RoomsRepository {

    override suspend fun getRooms(idHouse: String): Response<List<Room>> {
        return safeApiCall({true},
            { RoomsMapper.toDomain(service.getRooms(idHouse)) })
    }
}