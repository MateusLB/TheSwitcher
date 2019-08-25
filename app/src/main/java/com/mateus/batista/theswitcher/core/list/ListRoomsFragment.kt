package com.mateus.batista.theswitcher.core.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mateus.batista.domain.model.Room
import com.mateus.batista.theswitcher.R
import com.mateus.batista.theswitcher.base.BaseFragment
import com.mateus.batista.theswitcher.base.listeners.OnItemClickListener
import com.mateus.batista.theswitcher.utils.observeEvent
import kotlinx.android.synthetic.main.list_rooms_fragment.*
import kotlinx.android.synthetic.main.partial_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListRoomsFragment : BaseFragment(), OnItemClickListener<Room> {

    private lateinit var adapter: RoomAdapter
    private val viewModel: ListRoomsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRooms()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_rooms_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.getRoomStatus().observeEvent(this,
            loading = { progressBar.visibility = View.VISIBLE },

            success = {
                progressBar.visibility = View.GONE
                adapter = RoomAdapter(it, this)
                recycleView.adapter = adapter
            },

            error = {
                progressBar.visibility = View.GONE
                handleErrors(it) { viewModel.getRooms() }
            })
    }

    override fun onItemClick(item: Room, position: Int) {
        navController.navigate(ListRoomsFragmentDirections.actionListRoomsFragmentToDetailsRoomFragment(item.name, item.isLightOn))
    }
}
