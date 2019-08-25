package com.mateus.batista.data.remote

import com.mateus.batista.data.model.ApiRoom

interface SwitcherService {
    suspend fun getRooms(idHouse: String) : List<ApiRoom>
}