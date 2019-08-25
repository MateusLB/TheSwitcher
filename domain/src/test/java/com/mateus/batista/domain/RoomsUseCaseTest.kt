package com.mateus.batista.domain

import com.mateus.batista.domain.DataFactory.getDummyRooms
import com.mateus.batista.domain.DataFactory.randomString
import com.mateus.batista.domain.core.Response
import com.mateus.batista.domain.interactor.RoomsUseCase
import com.mateus.batista.domain.model.Room
import com.mateus.batista.domain.repository.RoomsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class RoomsUseCaseTest : KoinTest {

    private val roomsRepository = mock<RoomsRepository>()
    private lateinit var roomUseCase : RoomsUseCase

    @Before
    fun setup(){
        startKoin { modules(testModule) }
        roomUseCase = RoomsUseCase(roomsRepository, CoroutineScope(Dispatchers.Unconfined))
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun `RoomsUseCase WHEN passed valid params MUST call repository`() = runBlocking<Unit> {
        val idHouse = randomString()
        val dummyResult = getDummyRooms()

        stubRoomsRepositorySuccess(dummyResult)
        roomUseCase.run(RoomsUseCase.Params(idHouse))

        verify(roomsRepository).getRooms(idHouse)
    }

    @Test
    fun `RoomsUseCase WHEN missing params MUST throws exception`() = runBlocking {
        val error = assertFailsWith<IllegalArgumentException> { roomUseCase.run() }
        assertEquals("\"Params\" must not be null", error.message)
    }

    @Test
    fun `RoomsUseCase return a Success response`() = runBlocking<Unit> {
        val idHouse = randomString()
        val dummyResult = getDummyRooms()

        stubRoomsRepositorySuccess(dummyResult)
       val response =  roomUseCase.run(RoomsUseCase.Params(idHouse))

        assertTrue(response is Response.Success && response.data == dummyResult)
    }

    @Test
    fun `RoomsUseCase return a Error response`() = runBlocking<Unit> {
        val idHouse = randomString()
        val dummyResult = RuntimeException()

        stubRoomsRepositoryError(dummyResult)
        val response =  roomUseCase.run(RoomsUseCase.Params(idHouse))

        assertTrue(response is Response.Error && response.exception == dummyResult)
    }

    private fun stubRoomsRepositorySuccess(rooms: List<Room>) = runBlocking {
        whenever(roomsRepository.getRooms(any())).thenReturn(Response.Success(rooms))
    }

    private fun stubRoomsRepositoryError(error: Throwable) = runBlocking {
        whenever(roomsRepository.getRooms(any())).thenReturn(Response.Error(error))
    }
}