package com.example.cloudseis.presentation.ui.stations

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import info.devexchanges.databindingrecyclerview.databinding.ItemRecyclerViewBinding


class CountryViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
    private val binding: ItemRecyclerViewBinding
    fun bind(country: Country?) {
        binding.setCountry(country)
    }

    init {
        binding = DataBindingUtil.bind(view)
    }
}