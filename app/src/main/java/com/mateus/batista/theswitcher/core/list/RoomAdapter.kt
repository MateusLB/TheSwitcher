package com.mateus.batista.theswitcher.core.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mateus.batista.domain.model.Room
import com.mateus.batista.theswitcher.R
import com.mateus.batista.theswitcher.base.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_room.view.*

class RoomAdapter(
    private val list: List<Room>,
    private val listener: OnItemClickListener<Room>
) : RecyclerView.Adapter<RoomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindItemView(item){ listener.onItemClick(item, position) }
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItemView(item: Room, onItemClick: () -> Unit) {
            view.apply {
                nameText.text = item.name
                lightSwitch.isChecked = item.isLightOn

                lightSwitch.setOnCheckedChangeListener { _, isChecked ->
                    item.isLightOn = isChecked
                }
                setOnClickListener { onItemClick() }
            }
        }
    }
}