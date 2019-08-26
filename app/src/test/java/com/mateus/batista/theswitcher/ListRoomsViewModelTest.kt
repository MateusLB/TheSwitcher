package com.mateus.batista.theswitcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mateus.batista.domain.interactor.RoomsUseCase
import com.mateus.batista.domain.model.Room
import com.mateus.batista.theswitcher.core.list.ListRoomsViewModel
import com.mateus.batista.theswitcher.utils.FlowState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.assertEquals

class ListRoomsViewModelTest : KoinTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : ListRoomsViewModel

    private val roomsUseCase = mock<RoomsUseCase>()
    private val  testModule = module { single { roomsUseCase } }

    private val captor = argumentCaptor<((List<Room>) -> Unit)>()
    private val captorError = argumentCaptor<((Throwable) -> Unit)>()

    @Before
    fun setup(){
        startKoin { modules(testModule) }
        viewModel = ListRoomsViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `getRooms MUST postLoading WHEN called`(){
        viewModel.getRooms()
        verify(roomsUseCase).execute(params = any(), onError = any(), onSuccess = any())
        assertEquals(FlowState.Status.LOADING, viewModel.getRoomStatus().value?.status)
    }

    @Test
    fun `getRooms with SUCCESS response MUST postSuccess with data`(){
        val dummyListRoom = listOf<Room>()

        viewModel.getRooms()

        verify(roomsUseCase).execute(params = any(), onError = any(), onSuccess = captor.capture())
        captor.firstValue(dummyListRoom)

        assertEquals(FlowState.Status.SUCCESS, viewModel.getRoomStatus().value?.status)
        assertEquals(viewModel.getRoomStatus().value?.data, dummyListRoom)
    }

    @Test
    fun `getRooms with FAILURE response MUST postError with throwable`(){
        val dummyError = Throwable()
        viewModel.getRooms()

        verify(roomsUseCase).execute(params = any(), onError = captorError.capture(), onSuccess = any())
        captorError.firstValue(dummyError)

        assertEquals(FlowState.Status.ERROR, viewModel.getRoomStatus().value?.status)
        assertEquals(viewModel.getRoomStatus().value?.error, dummyError)
    }
}