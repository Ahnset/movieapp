package com.example.movieapp.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.detail.R
import com.example.movieapp.detail.databinding.ItemCastBinding
import com.example.movieapp.presentation.common.GlideHelper.setAsyncImageCircled
import com.example.movieapp.presentation.common.StringHelper.normalize
import com.example.movieapp.presentation.model.CastUI

class CastAdapter : ListAdapter<CastUI, CastAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cast, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCast = getItem(position)
        holder.setData(currentCast)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCastBinding.bind(view)

        fun setData(cast: CastUI) {
            binding.apply {
                itemCastName.text = cast.name.normalize()
                itemCastImg.setAsyncImageCircled(itemView.context, cast.profilePath)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<CastUI>() {
            override fun areItemsTheSame(
                oldItem: CastUI,
                newItem: CastUI
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CastUI,
                newItem: CastUI
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
