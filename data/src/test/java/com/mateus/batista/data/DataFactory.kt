package com.mateus.batista.data

import com.mateus.batista.data.model.ApiRoom
import com.mateus.batista.domain.model.Room
import java.util.*

object DataFactory {
    fun randomString() = UUID.randomUUID().toString()
    fun getDummyApiRooms() : List<ApiRoom> = listOf()
 }