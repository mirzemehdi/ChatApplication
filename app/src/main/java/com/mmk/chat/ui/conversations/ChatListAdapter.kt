package com.mmk.chat.ui.conversations

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.mmk.chat.R
import com.mmk.chat.databinding.ItemConversationBinding
import com.mmk.chat.model.ChatPOJO
import com.mmk.chat.model.GroupChatPOJO
import com.mmk.chat.model.NormalChatPOJO
import com.mmk.chat.model.UserPOJO
import com.mmk.chat.util.GenericRecyclerViewAdapter
import com.mmk.chat.util.MY_USER_ID

/**
 * Created by mirzemehdi on 1/27/21
 */
class ChatListAdapter :
    GenericRecyclerViewAdapter<ChatPOJO>(R.layout.item_conversation, ChatListItemCallback()) {


    override fun onBinding(item: ChatPOJO, binding: ViewDataBinding) {
        super.onBinding(item, binding)
        binding as ItemConversationBinding
        item.getLastMessage()?.let { msg ->
            if (msg.sender.id == MY_USER_ID) {
                binding.tvUserLastMessage.text =
                    binding.root.context.getString(R.string.last_message_me, msg.message)
            } else {
                binding.tvUserLastMessage.text = binding.root.context.getString(
                    R.string.last_message_other,
                    msg.sender.name,
                    msg.message
                )
            }

        }

        if (item is NormalChatPOJO) {
            val userOnlineIconColor = if (item.withUserPOJO.isOnline) {
                binding.root.resources.getColor(android.R.color.holo_green_light, null)
            } else
                binding.root.resources.getColor(android.R.color.darker_gray, null)
            binding.iconOnline.setBackgroundColor(userOnlineIconColor)
        }

    }

    internal class ChatListItemCallback : DiffUtil.ItemCallback<ChatPOJO>() {
        override fun areItemsTheSame(oldItem: ChatPOJO, newItem: ChatPOJO) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ChatPOJO, newItem: ChatPOJO): Boolean {
            return ((oldItem is NormalChatPOJO && newItem is NormalChatPOJO && (oldItem as NormalChatPOJO) == (newItem as NormalChatPOJO))
                    || (oldItem is GroupChatPOJO && newItem is GroupChatPOJO && (oldItem as GroupChatPOJO) == (newItem as GroupChatPOJO))

                    )
        }
    }
}
