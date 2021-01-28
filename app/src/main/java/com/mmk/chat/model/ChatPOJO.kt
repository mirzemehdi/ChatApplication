package com.mmk.chat.model

import kotlin.random.Random

/**
 * Created by mirzemehdi on 1/27/21
 */

//Chat can be either groupChat or normal chat
abstract class ChatPOJO constructor(val id: Int = Random.nextInt()) {
    val messageList = mutableListOf<MessagePOJO>()


    //Will be called when new message is sent
    fun addMessage(messagePOJO: MessagePOJO) {
        messageList.add(messagePOJO)
    }

    fun getLastMessage(): MessagePOJO? = messageList.lastOrNull()

    abstract fun getChatTitle(): String
    abstract fun getChatImage(): String?


}

