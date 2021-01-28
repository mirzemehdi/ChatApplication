package com.mmk.chat.ui.conversations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mmk.chat.repository.UserRepository
import com.mmk.chat.model.ChatPOJO
import com.mmk.chat.model.UserPOJO
import com.mmk.chat.network.NetworkResource
import com.mmk.chat.util.UiState
import kotlinx.coroutines.launch

/**
 * Created by mirzemehdi on 1/27/21
 */
class ConversationViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _chatList = MutableLiveData<List<ChatPOJO>>()
    val chatList: LiveData<List<ChatPOJO>> = _chatList

    private val _friendsList = MutableLiveData<List<UserPOJO>>()
    val friendsList: LiveData<List<UserPOJO>> = _friendsList

    private val _chatUiState = MutableLiveData<UiState>()
    val chatUiState: LiveData<UiState> = _chatUiState

    private val _friendsUiState = MutableLiveData<UiState>()
    val friendsUiState: LiveData<UiState> = _friendsUiState

    val searchQuery = MutableLiveData<String>()


    init {
        getMyConversations()
        getMyFriendList()
    }

    fun getMyFriendList() {
        viewModelScope.launch {
            _friendsUiState.value = UiState.Loading
            val response = userRepository.getMyFriendList(searchQuery.value)
            when (response) {
                is NetworkResource.Success -> {
                    _friendsList.value = response.data
                    _friendsUiState.value = UiState.HasData

                }

            }
        }
    }

    fun getMyConversations() {
        viewModelScope.launch {
            _chatUiState.value = UiState.Loading
            val response = userRepository.getMyConversations(searchQuery.value)
            when (response) {
                is NetworkResource.Success -> {
                    _chatList.value = response.data
                    _chatUiState.value = UiState.HasData

                }

            }
        }
    }
}