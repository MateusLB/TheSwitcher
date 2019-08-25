package com.mateus.batista.domain

import com.mateus.batista.domain.model.Room
import java.util.*

object DataFactory {
    fun randomString() = UUID.randomUUID().toString()
    fun getDummyRooms() : List<Room> = listOf()
 }