package com.mmk.chat.ui.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmk.chat.R
import com.mmk.chat.repository.UserRepository
import com.mmk.chat.model.MessagePOJO
import com.mmk.chat.util.MY_USER_ID

/**
 * Created by mirzemehdi on 1/27/21
 */
class ChatAdapter : ListAdapter<MessagePOJO, RecyclerView.ViewHolder>(MessageItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_chat_me -> MyUserChatViewHolder.from(parent)
            R.layout.item_chat_other_person -> OtherUserChatViewHolder.from(parent)
            else -> OtherUserChatViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)
        val item = getItem(position)

        item?.let {
            when (itemViewType) {
                R.layout.item_chat_me -> (holder as MyUserChatViewHolder).bind(it)
                R.layout.item_chat_other_person -> (holder as OtherUserChatViewHolder).bind(it)
            }

        }

    }

    override fun getItemViewType(position: Int): Int {

        return when (getItem(position).sender.id) {
            MY_USER_ID -> R.layout.item_chat_me
            else -> R.layout.item_chat_other_person

        }
    }

    internal class MessageItemCallback : DiffUtil.ItemCallback<MessagePOJO>() {
        override fun areItemsTheSame(oldItem: MessagePOJO, newItem: MessagePOJO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MessagePOJO, newItem: MessagePOJO): Boolean {
            return oldItem == newItem
        }
    }

}
