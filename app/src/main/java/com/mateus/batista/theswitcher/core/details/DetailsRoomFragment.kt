package com.mateus.batista.theswitcher.core.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mateus.batista.theswitcher.R
import com.mateus.batista.theswitcher.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class DetailsRoomFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_room, container, false)
    }
}
