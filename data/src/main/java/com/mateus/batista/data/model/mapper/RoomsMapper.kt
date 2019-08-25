package com.mateus.batista.data.model.mapper

import com.mateus.batista.data.model.ApiRoom
import com.mateus.batista.domain.model.Room

object RoomsMapper : DataMapper<List<ApiRoom>, List<Room>>() {
    override fun toDomain(data: List<ApiRoom>): List<Room> {
        return data.map { apiRoom ->
            Room(
                name = apiRoom.name ?: "",
                isLightOn = apiRoom.isLightOn ?: false
            )
        }
    }
}