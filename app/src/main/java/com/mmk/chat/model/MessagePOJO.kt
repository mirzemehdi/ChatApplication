package com.mmk.chat.model

import kotlin.random.Random

/**
 * Created by mirzemehdi on 1/27/21
 */

//User can send either Text or Image type message (default is Text)
data class MessagePOJO(
    val sender: UserPOJO,
    val message: String = "",
    val sendingTime: String = "",
    val id: Int = Random.nextInt(),
    val type: MessageType = MessageType.TEXT
) {

    enum class MessageType {
        TEXT,
        IMAGE
    }
}