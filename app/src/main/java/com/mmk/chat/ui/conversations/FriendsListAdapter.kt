package com.mmk.chat.ui.conversations

import androidx.recyclerview.widget.DiffUtil
import com.mmk.chat.R
import com.mmk.chat.model.UserPOJO
import com.mmk.chat.util.GenericRecyclerViewAdapter

/**
 * Created by mirzemehdi on 1/27/21
 */
class FriendsListAdapter :
    GenericRecyclerViewAdapter<UserPOJO>(R.layout.item_user_story, FriendsListItemCallback()) {


    class FriendsListItemCallback : DiffUtil.ItemCallback<UserPOJO>() {
        override fun areItemsTheSame(oldItem: UserPOJO, newItem: UserPOJO) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserPOJO, newItem: UserPOJO) = oldItem == newItem
    }
}
