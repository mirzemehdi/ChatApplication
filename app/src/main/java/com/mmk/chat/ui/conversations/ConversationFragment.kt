package com.mmk.chat.ui.conversations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.mmk.chat.R
import com.mmk.chat.databinding.FragmentConversationBinding
import com.mmk.chat.model.NormalChatPOJO
import com.mmk.chat.ui.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by mirzemehdi on 1/27/21
 */
@AndroidEntryPoint
class ConversationFragment : Fragment(R.layout.fragment_conversation) {
    private lateinit var binding: FragmentConversationBinding
    private val viewModel: ConversationViewModel by viewModels()


    private val friendsListAdapter = FriendsListAdapter()
    private val chatListAdapter = ChatListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConversationBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        initView()
        observeValues()
        setClicks()

    }

    private fun initView() {
        with(binding) {
            friendsRecyclerView.adapter = friendsListAdapter

            chatsRecyclerView.adapter = chatListAdapter
        }
    }


    private fun observeValues() {
        viewModel.friendsList.observe(viewLifecycleOwner) {
            friendsListAdapter.submitList(it)
        }
        viewModel.chatList.observe(viewLifecycleOwner) {
            chatListAdapter.submitList(it)
        }
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            viewModel.getMyConversations()
            viewModel.getMyFriendList()
        }
    }

    private fun setClicks() {
        chatListAdapter.onClickItem = {
            findNavController().navigate(
                ConversationFragmentDirections.actionConversationFragmentToChatFragment(
                    it.id
                )
            )
        }

        friendsListAdapter.onClickItem = { user ->
            val chatId = chatListAdapter.currentList.find {
                it is NormalChatPOJO && it.withUserPOJO.id == user.id
            }
            chatId?.let {
                findNavController().navigate(
                    ConversationFragmentDirections.actionConversationFragmentToChatFragment(
                        it.id
                    )
                )
            }

        }
    }


}