package com.mateus.batista.domain.repository

import com.mateus.batista.domain.core.Response
import com.mateus.batista.domain.model.Room

interface RoomsRepository {

    suspend fun getRooms(idHouse : String) : Response<List<Room>>
}