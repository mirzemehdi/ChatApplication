package com.mmk.chat.ui.chat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mmk.chat.R
import com.mmk.chat.databinding.FragmentChatBinding
import com.mmk.chat.databinding.FragmentConversationBinding
import com.mmk.chat.model.ChatPOJO
import com.mmk.chat.model.GroupChatPOJO
import com.mmk.chat.model.NormalChatPOJO
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by mirzemehdi on 1/27/21
 */
@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private val args: ChatFragmentArgs by navArgs()
    private val chatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getChat(args.chatId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initView()
        observeValues()
        setClicks()


    }


    private fun initView() {
        with(binding) {
            messagesRecyclerView.setHasFixedSize(true)
            messagesRecyclerView.adapter = chatAdapter

            //This helps to scroll to the last message when keyboard is opened
            messagesRecyclerView.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
                if (bottom < oldBottom)
                    messagesRecyclerView.layoutManager?.smoothScrollToPosition(
                        messagesRecyclerView,
                        null,
                        chatAdapter.itemCount
                    )
            }
        }
    }


    private fun observeValues() {
        viewModel.chat.observe(viewLifecycleOwner) {
            chatAdapter.submitList(it.messageList)
            binding.messagesRecyclerView.scrollToPosition(it.messageList.size - 1)
            initToolbar(it)

        }
    }

    private fun setClicks() {
        binding.toolbar.iconBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initToolbar(chatPOJO: ChatPOJO) {

        when (chatPOJO) {
            is GroupChatPOJO -> {
                binding.toolbar.tvUserLastSeen.isVisible = false
            }
            is NormalChatPOJO -> {
                binding.toolbar.tvUserLastSeen.isVisible = true
                val lastSeenTime = chatPOJO.withUserPOJO.lastSeen
                binding.toolbar.tvUserLastSeen.text =
                    if (chatPOJO.withUserPOJO.isOnline) getString(R.string.text_active_now)
                    else getString(R.string.last_seen_time, lastSeenTime)
            }
        }

    }


}