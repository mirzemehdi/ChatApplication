package com.mmk.chat.model

/**
 * Created by mirzemehdi on 1/27/21
 */
data class GroupChatPOJO(
    val groupName: String,
    val groupImage: String,
    val usersList: MutableList<UserPOJO>
) : ChatPOJO() {
    override fun getChatTitle(): String = groupName

    override fun getChatImage(): String? =groupImage

    //Will be called when new user is added to the group
    fun addUser(userPOJO: UserPOJO) {
        usersList.add(userPOJO)
    }
}