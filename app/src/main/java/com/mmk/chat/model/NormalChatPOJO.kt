package com.mmk.chat.model

/**
 * Created by mirzemehdi on 1/27/21
 */
data class NormalChatPOJO(val withUserPOJO: UserPOJO):ChatPOJO() {

    override fun getChatTitle(): String =withUserPOJO.fullName

    override fun getChatImage(): String? =withUserPOJO.profileImageUrl
}