package com.mmk.chat.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmk.chat.repository.UserRepository
import com.mmk.chat.model.ChatPOJO
import com.mmk.chat.model.MessagePOJO
import com.mmk.chat.model.UserPOJO
import com.mmk.chat.network.NetworkResource
import com.mmk.chat.util.UiState
import kotlinx.coroutines.launch

/**
 * Created by mirzemehdi on 1/27/21
 */
class ChatViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var chatId: Int? = null
    private val _chat = MutableLiveData<ChatPOJO>()
    val chat: LiveData<ChatPOJO> = _chat

    private val _chatUiState = MutableLiveData<UiState>()
    val chatUiState: LiveData<UiState> = _chatUiState

    //Represents user's message
    val messageText = MutableLiveData("")


    fun getChat(chatId: Int) {
        this.chatId = chatId
        viewModelScope.launch {
            _chatUiState.value = UiState.Loading
            val response = userRepository.getChatById(chatId)
            when (response) {
                is NetworkResource.Success -> {
                    _chat.value = response.data
                    _chatUiState.value = UiState.HasData
                }

            }
        }
    }

    fun onClickSendMessage() {
        messageText.value?.let { msg ->
            val messagePOJO = MessagePOJO(userRepository.myUser, msg, "10:08 AM")
            chatId?.let {
                viewModelScope.launch {
                    val response = userRepository.sendMessage(it, messagePOJO)
                    when (response) {
                        is NetworkResource.Success -> {
                            _chat.value = response.data
                            messageText.value = "" //Remove message in order to write new message
                        }
                    }
                }
            }
        }
    }


}