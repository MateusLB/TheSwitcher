package com.mateus.batista.theswitcher.core.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mateus.batista.domain.interactor.RoomsUseCase
import com.mateus.batista.domain.model.Room
import com.mateus.batista.theswitcher.base.BaseViewModel
import com.mateus.batista.theswitcher.utils.*

class ListRoomsViewModel : BaseViewModel() {

    private val roomStatus by lazy { MutableLiveData<FlowState<List<Room>>>() }
    fun getRoomStatus(): LiveData<FlowState<List<Room>>> = roomStatus

    private val roomUseCase: RoomsUseCase by useCase()

    fun getRooms() {
        roomStatus.postLoading()
        roomUseCase.execute(
            params = RoomsUseCase.Params(""),
            onSuccess = { roomStatus.postSuccess(it) },
            onError = { roomStatus.postError(it) }
        )
    }
}