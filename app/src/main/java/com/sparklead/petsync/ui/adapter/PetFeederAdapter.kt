package com.sparklead.petsync.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.petsync.databinding.ItemPetFeederBinding
import com.sparklead.petsync.dto.FeedDto
import com.sparklead.petsync.utils.Constants

class PetFeederAdapter : RecyclerView.Adapter<PetFeederAdapter.PetViewHolder>() {

    inner class PetViewHolder(val binding: ItemPetFeederBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<FeedDto>() {
        override fun areItemsTheSame(oldItem: FeedDto, newItem: FeedDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedDto, newItem: FeedDto): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        return PetViewHolder(
            ItemPetFeederBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        with(holder) {
            with(differ.currentList[position]) {
                binding.apply {
                    tvPetName.text = pet
                    tvTime.text = Constants.convertTimestampToDate(timestamp).second
                    tvDate.text = Constants.convertTimestampToDate(timestamp).first
                    tvAgo.text = Constants.timeDifference(timestamp)
                }
            }
        }
    }
}