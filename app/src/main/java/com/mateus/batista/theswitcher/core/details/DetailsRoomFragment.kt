package com.mateus.batista.theswitcher.core.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.mateus.batista.theswitcher.R
import com.mateus.batista.theswitcher.base.BaseFragment
import com.mateus.batista.theswitcher.core.MainActivity
import kotlinx.android.synthetic.main.fragment_details_room.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsRoomFragment : BaseFragment() {

    private val detailsArgs : DetailsRoomFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar!!.title = detailsArgs.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
       detailText.text = getString(R.string.detail_room, detailsArgs.name)
        when(detailsArgs.isLightOn){
            true -> {
                lightImage.setBackgroundResource(R.drawable.light_on)
                isLightText.text = getString(R.string.light_on)
            }
            false -> {
                lightImage.setBackgroundResource(R.drawable.light_off)
                isLightText.text = getString(R.string.light_off)
            }
        }
    }
}
