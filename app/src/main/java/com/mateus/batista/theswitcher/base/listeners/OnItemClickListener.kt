package com.mateus.batista.theswitcher.base.listeners

interface OnItemClickListener<T> {

    fun onItemClick(item: T, position: Int)
}