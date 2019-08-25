package com.mateus.batista.data.remote

import com.mateus.batista.data.model.ApiRoom
import kotlinx.coroutines.delay

class FakeService : SwitcherService {

    override suspend fun getRooms(idHouse: String): List<ApiRoom> {
        delay(2000) //simulate the time of a call
        return listOf(
            ApiRoom("Kitchen", false),
            ApiRoom("Living room", false),
            ApiRoom("Master bedroom", false),
            ApiRoom("Guest’s bedroom", false))
    }
}