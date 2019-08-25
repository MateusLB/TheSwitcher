package com.mateus.batista.data

import com.mateus.batista.data.DataFactory.getDummyApiRooms
import com.mateus.batista.data.DataFactory.randomString
import com.mateus.batista.data.model.ApiRoom
import com.mateus.batista.data.remote.SwitcherService
import com.mateus.batista.data.repository.RoomsRepositoryImp
import com.mateus.batista.domain.core.Response
import com.mateus.batista.domain.repository.RoomsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(JUnit4::class)
class RoomsRepositoryTest : KoinTest {

    private lateinit var roomsRepository : RoomsRepository
    private val service = mock<SwitcherService>()

    @Before
    fun setup(){
        startKoin {}
        roomsRepository = RoomsRepositoryImp(service)
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun `getRooms When return Exception MUST return a Error response`() = runBlocking {
        val idHouse = randomString()
        val dummyError = Throwable()

        stubServiceError(dummyError)
        val response  = roomsRepository.getRooms(idHouse)

        assertTrue(response is Response.Error)
    }

    @Test
    fun `getRooms When API return success MUST return a Success response`() = runBlocking {
        val idHouse = randomString()
        val dummyApiRoom = getDummyApiRooms()

        stubServiceSuccess(dummyApiRoom)
        val response = roomsRepository.getRooms(idHouse)

        kotlin.test.assertTrue(response is Response.Success && response.data == dummyApiRoom)
    }

    private fun stubServiceSuccess(rooms: List<ApiRoom>) = runBlocking {
        whenever(service.getRooms(any())).thenReturn(rooms)
    }

    private fun stubServiceError(error: Throwable) = runBlocking {
        given(service.getRooms(any())).willAnswer { error }
    }
}