package com.mmk.chat.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mmk.chat.databinding.ItemChatMeBinding
import com.mmk.chat.model.MessagePOJO

/**
 * Created by mirzemehdi on 27/01/21
 */
class MyUserChatViewHolder private constructor(private val binding: ItemChatMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MessagePOJO) {
        binding.apply {
            listItem = item
            imageMessage.isVisible = item.type == MessagePOJO.MessageType.IMAGE
            tvMessage.isVisible = item.type == MessagePOJO.MessageType.TEXT
            executePendingBindings()
        }

    }

    companion object {
        fun from(parent: ViewGroup): MyUserChatViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemChatMeBinding.inflate(inflater, parent, false)
            return MyUserChatViewHolder(binding)
        }
    }
}