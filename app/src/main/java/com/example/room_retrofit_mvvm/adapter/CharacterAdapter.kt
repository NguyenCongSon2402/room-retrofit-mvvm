package com.example.room_retrofit_mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitroom.database.model.Character
import com.example.room_retrofit_mvvm.databinding.ItemCharacterBinding


class CharacterAdapter(private val context: Context, private val mList: ArrayList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (!mList.isNullOrEmpty())
            return mList.size
        return 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=mList[position]
        holder.binding.itemName.text=item.name
        Glide.with(context).load(item.image).into(holder.binding.itemImg)
    }
}